package com.github.blanexie.vxpt.tracker.controller.config

import cn.hutool.core.lang.Singleton
import cn.hutool.core.util.ClassUtil
import com.github.blanexie.vxpt.tracker.common.Mapping
import com.github.blanexie.vxpt.tracker.common.MappingMethodInfo
import com.github.blanexie.vxpt.tracker.repository.loadProperties
import io.netty.bootstrap.ServerBootstrap
import io.netty.buffer.Unpooled
import io.netty.channel.*
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.codec.http.*
import io.netty.handler.codec.http.HttpHeaderNames.CONNECTION
import io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH
import io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE
import io.netty.handler.codec.http.HttpHeaderValues.CLOSE
import io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE
import io.netty.handler.codec.http.HttpHeaderValues.TEXT_PLAIN
import io.netty.handler.codec.http.HttpResponseStatus.OK
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler
import io.netty.handler.ssl.SslContext
import org.slf4j.LoggerFactory
import java.lang.reflect.Method


class HttpTrackerServer {

    private val log = LoggerFactory.getLogger(HttpTrackerServer::class.java)

    fun httpStart() {
        val SSL = System.getProperty("ssl") != null
        val PORT = System.getProperty("port").toInt()

        val sslCtx: SslContext? = ServerUtil.buildSslContext()

        val bossGroup: EventLoopGroup = NioEventLoopGroup(1)
        val workerGroup: EventLoopGroup = NioEventLoopGroup()
        try {
            val b = ServerBootstrap()
            b.option(ChannelOption.SO_BACKLOG, 1024)
            b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel::class.java)
                .handler(LoggingHandler(LogLevel.INFO))
                .childHandler(HttpTrackerServerInitializer(sslCtx))
            val ch: Channel = b.bind(PORT).sync().channel()
            log.info(
                "Open your web browser and navigate to " +
                        (if (SSL) "https" else "http") + "://127.0.0.1:" + PORT + '/'
            )
            ch.closeFuture().sync()
        } finally {
            bossGroup.shutdownGracefully()
            workerGroup.shutdownGracefully()
        }
    }
}


class HttpTrackerServerInitializer(private val sslCtx: SslContext?) : ChannelInitializer<SocketChannel>() {
    public override fun initChannel(ch: SocketChannel) {
        val p: ChannelPipeline = ch.pipeline()
        if (sslCtx != null) {
            p.addLast(sslCtx.newHandler(ch.alloc()))
        }
        p.addLast(HttpServerCodec())
        p.addLast(HttpServerExpectContinueHandler())
        p.addLast(HttpTrackerServerHandler())
    }
}


class HttpTrackerServerHandler : SimpleChannelInboundHandler<HttpObject>() {
    override fun channelReadComplete(ctx: ChannelHandlerContext) {
        ctx.flush()
    }

    public override fun channelRead0(ctx: ChannelHandlerContext, msg: HttpObject) {
        if (msg is HttpRequest) {
            val req: HttpRequest = msg as HttpRequest
            val uri = req.uri().split("?").first()

            val mappingMethodInfo = Singleton.get("_" + uri) {
                throw Error("uri: $uri 未找到对应的method")
            } as MappingMethodInfo

            var obj = Singleton.get(mappingMethodInfo.clazz)
            var response = mappingMethodInfo.method.invoke(obj, req) as FullHttpResponse

//            val response: FullHttpResponse = DefaultFullHttpResponse(
//                req.protocolVersion(), OK,
//                Unpooled.wrappedBuffer(CONTENT)
//            )

            val keepAlive: Boolean = HttpUtil.isKeepAlive(req)

            response.headers()
                .set(CONTENT_TYPE, TEXT_PLAIN)
                .setInt(CONTENT_LENGTH, response.content().readableBytes())
            if (keepAlive) {
                if (!req.protocolVersion().isKeepAliveDefault) {
                    response.headers()[CONNECTION] = KEEP_ALIVE
                }
            } else {
                // Tell the client we're going to close the connection.
                response.headers()[CONNECTION] = CLOSE
            }
            val f = ctx.write(response)
            if (!keepAlive) {
                f.addListener(ChannelFutureListener.CLOSE)
            }
        }
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        cause.printStackTrace()
        ctx.close()
    }

    companion object {
        private val CONTENT = byteArrayOf(
            'H'.toByte(),
            'e'.toByte(),
            'l'.toByte(),
            'l'.toByte(),
            'o'.toByte(),
            ' '.toByte(),
            'W'.toByte(),
            'o'.toByte(),
            'r'.toByte(),
            'l'.toByte(),
            'd'.toByte()
        )
    }
}