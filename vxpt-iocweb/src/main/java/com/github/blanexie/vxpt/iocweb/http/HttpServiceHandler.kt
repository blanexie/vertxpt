package com.github.blanexie.vxpt.iocweb.http

import com.github.blanexie.vxpt.ioc.annotation.Component
import com.github.blanexie.vxpt.ioc.annotation.Inject
import com.github.blanexie.vxpt.iocweb.entity.MappingHandler
import com.github.blanexie.vxpt.iocweb.filter.FilterHandler
import io.netty.channel.ChannelFutureListener
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.handler.codec.http.*
import io.netty.handler.codec.http.cookie.ServerCookieDecoder
import io.netty.util.internal.StringUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.reflect.InvocationTargetException

@ChannelHandler.Sharable
@Component
class HttpServiceHandler : SimpleChannelInboundHandler<FullHttpRequest>() {

    private val log: Logger = LoggerFactory.getLogger(HttpServiceHandler::class.java)

    @Inject
    lateinit var mappingHandler: MappingHandler

    @Inject
    lateinit var filterHandler: FilterHandler

    override fun channelReadComplete(ctx: ChannelHandlerContext) {
        ctx.flush()
    }

    @Throws(InvocationTargetException::class, IllegalAccessException::class)
    public override fun channelRead0(ctx: ChannelHandlerContext, req: FullHttpRequest) {
        //获取url中的参数
        val decoder = QueryStringDecoder(req.uri())
        val path = decoder.path()
        val paramList: Map<String, List<String>> = decoder.parameters()


        //执行拦截器的 前置拦截
        var response = filterHandler.processPre(path, req)
        if (response == null) {
            //拦截器执行完成
            response = mappingHandler.process(path, req)
        }
        //执行拦截器的 后置拦截
        val result = filterHandler.processPost(path, req, response)
        if (!result) {
            log.info("后置拦截器 提前返回，未全部执行完成")
        }
        //响应的设置
        val keepAlive = HttpUtil.isKeepAlive(req)
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.TEXT_PLAIN)
            .setInt(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes())
        if (keepAlive) {
            if (!req.protocolVersion().isKeepAliveDefault) {
                response.headers()[HttpHeaderNames.CONNECTION] = HttpHeaderValues.KEEP_ALIVE
            }
        } else {
            // Tell the client we're going to close the connection.
            response.headers()[HttpHeaderNames.CONNECTION] = HttpHeaderValues.CLOSE
        }
        val f = ctx.write(response)
        if (!keepAlive) {
            f.addListener(ChannelFutureListener.CLOSE)
        }
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        log.error("", cause)
        ctx.close()
    }

    fun buildHttpReq(fullHttpRequest: FullHttpRequest): HttpReq {
        //获取url中的参数
        val decoder = QueryStringDecoder(fullHttpRequest.uri())
        val path = decoder.path()
        var parameters = decoder.parameters()
        //获取请求头信息
        var headers = fullHttpRequest.headers()
        //获取cookie信息
        val cookieString: String = fullHttpRequest.headers().get(HttpHeaderNames.COOKIE)
        val cookies = cookieString ?: ServerCookieDecoder.STRICT.decode(cookieString)
        //获取method
        var method = fullHttpRequest.method()
        if(method == HttpMethod.POST){
            // 处理post 请求
            val strContentType = fullHttpRequest.headers()["Content-Type"].trim { it <= ' ' }
            if (strContentType.contains("x-www-form-urlencoded")) {
                params = getFormParams(fullHttpRequest)
            } else if(strContentType.contains("multipart/form-data")){

            } else if (strContentType.contains("application/json")) {
                params = getJSONParams(fullHttpRequest)
            } else {
                return null
            }
        }

    }


}