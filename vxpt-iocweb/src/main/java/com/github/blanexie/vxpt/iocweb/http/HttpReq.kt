package com.github.blanexie.vxpt.iocweb.http

import cn.hutool.core.lang.Dict
import io.netty.buffer.ByteBuf
import io.netty.handler.codec.http.HttpHeaders
import io.netty.handler.codec.http.HttpMethod
import io.netty.handler.codec.http.cookie.Cookie
import io.netty.handler.codec.http.multipart.InterfaceHttpData


class HttpReq(
    val path: String,
    val method: HttpMethod,
    val cookies: Set<Cookie>,
    val header: HttpHeaders,
    val params: Map<String, List<String>>,
    val body: List<InterfaceHttpData>,
    val attribute: Dict
)

