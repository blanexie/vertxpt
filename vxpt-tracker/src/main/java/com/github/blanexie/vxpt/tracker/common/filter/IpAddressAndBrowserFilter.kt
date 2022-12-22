package com.github.blanexie.vxpt.tracker.common.filter

import cn.hutool.core.lang.RegexPool
import com.github.blanexie.vxpt.ioc.annotation.Component
import  com.github.blanexie.vxpt.iocweb.filter.Filter
import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufAllocator
import io.netty.buffer.ByteBufUtil
import io.netty.buffer.Unpooled
import io.netty.handler.codec.http.*
import java.net.InetAddress
import java.net.InetSocketAddress
import java.util.regex.Pattern


@Component
class IpAddressAndBrowserFilter : Filter {


    override fun regexPath(path: String): Boolean {
        return path.equals("/announce")
    }

    override fun pre(req: HttpRequest): FullHttpResponse? {
        return if (!blockBrowser(req)) {
            DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.NOT_ACCEPTABLE,
                Unpooled.wrappedBuffer("not allow client".toByteArray())
            )
        } else {
            var ipAddress = getIpAddress(req)
            return null
        }
    }

    override fun post(req: HttpRequest, resp: FullHttpResponse): Boolean {
        TODO("Not yet implemented")
    }

    private fun blockBrowser(request: HttpRequest): Boolean {
        val userAgent = request.headers().get("user-agent")
        if (userAgent != null) {
            val acceptUserAgent = userAgent.contains("/^Mozilla/") || userAgent.contains("/^Opera/")
                    || userAgent.contains("/^Links/") || userAgent.contains("/^Lynx/")
            if (acceptUserAgent) {
                return false
            }
        }
        return true
    }

    private val LOCALHOST = "127.0.0.1"
    private val LOCALHOSTIPV6 = "0:0:0:0:0:0:0:1"
    private val LOCALHOSTStr = "localhost"
    private val DOCKERLOCLHOST = "host.docker.internal"

    /**
     * 注意对ipv6地址的兼容和处理
     */
    fun getIpAddress(request: HttpRequest): String? {
        val UNKNOWN = "unknown";
        var ipAddress = request.headers().get("x-forwarded-for")
        if (ipAddress == null || ipAddress.isEmpty() || UNKNOWN != ipAddress) {
            ipAddress = request.headers().get("Proxy-Client-IP")
        }
        if (ipAddress == null || ipAddress.isEmpty() || UNKNOWN != ipAddress) {
            ipAddress = request.headers().get("WL-Proxy-Client-IP")
        }
        if (ipAddress == null || ipAddress.isEmpty() || UNKNOWN != ipAddress) {
            ipAddress = request.headers().get("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || UNKNOWN != ipAddress) {
            ipAddress = request.headers().get("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.isEmpty() || UNKNOWN != ipAddress) {
            ipAddress = request.headers().get("X-Real-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || UNKNOWN != ipAddress) {
            val remoteAddress = request.headers().get("remoteAddress")
            val inetSocketAddress = remoteAddress.first() as InetSocketAddress
            ipAddress = inetSocketAddress.address.hostAddress
            if (LOCALHOST == ipAddress
                || LOCALHOSTStr == ipAddress
                || LOCALHOSTIPV6 == ipAddress
                || DOCKERLOCLHOST == ipAddress
            ) {
                var inet: InetAddress? = InetAddress.getLocalHost()
                ipAddress = inet!!.hostAddress
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        // "***.***.***.***".length()
        if (ipAddress != null && ipAddress.length > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","))
            }
        }
        return ipAddress
    }


}