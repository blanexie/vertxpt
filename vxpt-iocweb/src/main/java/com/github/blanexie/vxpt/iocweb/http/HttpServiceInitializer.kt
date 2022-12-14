package com.github.blanexie.vxpt.iocweb.http

import com.github.blanexie.vxpt.ioc.annotation.Component
import com.github.blanexie.vxpt.ioc.annotation.Inject
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.http.HttpServerCodec
import io.netty.handler.codec.http.HttpServerExpectContinueHandler

@Component
class HttpServiceInitializer : ChannelInitializer<SocketChannel>() {

    @Inject
    lateinit var httpServiceHandler: HttpServiceHandler

    @Throws(Exception::class)
    override fun initChannel(ch: SocketChannel) {
        val p = ch.pipeline()
        p.addLast(HttpServerCodec())
        p.addLast(HttpServerExpectContinueHandler())
        p.addLast(httpServiceHandler)
    }
}