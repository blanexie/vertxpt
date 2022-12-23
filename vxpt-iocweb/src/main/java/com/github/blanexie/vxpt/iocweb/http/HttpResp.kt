package com.github.blanexie.vxpt.iocweb.http

import io.netty.handler.codec.http.cookie.Cookie
import io.netty.handler.codec.http.multipart.InterfaceHttpData

class HttpResp(
    val path: String,
    val method: String,
    val header: Map<String, List<String>>,
    val cookies: Set<Cookie>,
    val params: Map<String, List<String>>,
    val body: List<InterfaceHttpData>,
    val responseCode: Int,
    val httpReq: HttpReq
)