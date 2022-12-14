package com.github.blanexie.vxpt.iocweb.filter

import cn.hutool.cache.CacheUtil
import cn.hutool.cache.impl.LFUCache
import cn.hutool.core.lang.Singleton
import com.github.blanexie.vxpt.ioc.annotation.Component
import io.netty.handler.codec.http.FullHttpResponse
import io.netty.handler.codec.http.HttpRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.streams.toList

@Component
class FilterHandler {

    private val log: Logger = LoggerFactory.getLogger(FilterHandler::class.java)

    private val filterCache: LFUCache<String, List<out Filter>> = CacheUtil.newLFUCache(100, 30 * 60 * 1000L)

    fun handlerPre(path: String, httpRequest: HttpRequest): FullHttpResponse? {
        val handler: List<out Filter> = getSupportFilters(path)
        for (filter in handler) {
            var resp = filter.pre(httpRequest)
            if (resp != null) {
                log.info("filter:{} 前置拦截执行了", filter)
                return resp
            }
        }
        return null
    }

    fun handlerPost(path: String, httpRequest: HttpRequest, response: FullHttpResponse): Boolean {
        val handler: List<out Filter> = getSupportFilters(path)
        for (filter in handler) {
            var result = filter.post(httpRequest, response)
            if (!result) {
                log.info("filter:{} 后置拦截执行了", filter)
                return result
            }
        }
        return true
    }

    private fun getSupportFilters(path: String): List<out Filter> {
        val handler: List<out Filter> = filterCache.get(
            path, Singleton.getExistClass().map { Singleton.get(it) }
            .map {
                if (it is Filter) {
                    it
                } else {
                    null
                }
            }.filterNotNull()
            .filter { it.regexPath(path) }
            .sortedBy { it.order() }::toList
        )
        return handler
    }
}