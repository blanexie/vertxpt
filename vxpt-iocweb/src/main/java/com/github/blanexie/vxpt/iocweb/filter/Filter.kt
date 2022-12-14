package com.github.blanexie.vxpt.iocweb.filter

import io.netty.handler.codec.http.FullHttpResponse
import io.netty.handler.codec.http.HttpRequest

interface Filter {

    fun order(): Int {
        return 0
    }

    /**
     * 路径匹配， 才会执行前置和后置方法
     */
    fun regexPath(path: String): Boolean

    /**
     * 前置拦截， 如果返回值不为空，就直接拦截结束，否则放行
     */
    fun pre(req: HttpRequest): FullHttpResponse?

    /**
     * 后置拦截， 如果返回true， 继续执行其他的后置拦截器，否则直接返回
     */
    fun post(req: HttpRequest, resp: FullHttpResponse): Boolean
}