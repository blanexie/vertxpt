package com.github.blanexie.vxpt.iocweb.entity


import cn.hutool.core.lang.Singleton
import cn.hutool.core.util.ReferenceUtil
import com.github.blanexie.vxpt.ioc.annotation.Component
import com.github.blanexie.vxpt.iocweb.annotation.Mapping
import io.netty.buffer.Unpooled
import io.netty.handler.codec.http.DefaultFullHttpResponse
import io.netty.handler.codec.http.FullHttpResponse
import io.netty.handler.codec.http.HttpRequest
import io.netty.handler.codec.http.HttpResponseStatus
import io.netty.util.internal.ReflectionUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.charset.StandardCharsets
import kotlin.jvm.internal.Reflection
import kotlin.reflect.jvm.kotlinFunction


@Component
class MappingHandler {

    private val log: Logger = LoggerFactory.getLogger(MappingHandler::class.java)

    private val mappingInfoMap = hashMapOf<String, MappingInfo>()


    fun process(path: String, httpRequest: HttpRequest): FullHttpResponse {
        val mappingInfo = getMappingInfo(path)
            ?: return DefaultFullHttpResponse(
                httpRequest.protocolVersion(), HttpResponseStatus.NOT_FOUND,
                Unpooled.wrappedBuffer("$path not found".toByteArray(StandardCharsets.UTF_8))
            )
        val obj = Singleton.get(mappingInfo.clazz.java)
        val resp = mappingInfo.method.call(obj, httpRequest)
        return resp as FullHttpResponse
    }

    private fun getMappingInfo(path: String): MappingInfo? {
        if (mappingInfoMap.isEmpty()) {
            Singleton.getExistClass()
                .map {
                    it.declaredMethods.filter { m -> m.getAnnotation(Mapping::class.java) != null }
                        .map { m ->
                            ReflectionUtil.trySetAccessible(m, false)
                            MappingInfo(m.getAnnotation(Mapping::class.java), m.kotlinFunction!!, it.kotlin)
                        }.toList()
                }.flatMap { it.asIterable() }
                .forEach { mappingInfoMap[it.mapping.path] = it }
        }
        return mappingInfoMap[path]
    }

}
