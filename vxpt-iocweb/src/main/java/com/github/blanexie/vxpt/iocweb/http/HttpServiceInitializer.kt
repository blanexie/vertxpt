package com.github.blanexie.vxpt.iocweb.http

import com.github.blanexie.vxpt.ioc.annotation.Component
import com.github.blanexie.vxpt.ioc.annotation.Inject
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.http.HttpObjectAggregator
import io.netty.handler.codec.http.HttpServerCodec
import io.netty.handler.codec.http.HttpServerExpectContinueHandler
import io.netty.handler.stream.ChunkedWriteHandler

@Component
class HttpServiceInitializer : ChannelInitializer<SocketChannel>() {

    @Inject
    lateinit var httpServiceHandler: HttpServiceHandler

    @Throws(Exception::class)
    override fun initChannel(ch: SocketChannel) {
        val pipeline = ch.pipeline()
        pipeline.addLast(HttpServerCodec())
        pipeline.addLast(HttpObjectAggregator(1024 * 1024))
        pipeline.addLast(HttpServerExpectContinueHandler())
        pipeline .addLast(ChunkedWriteHandler())
        pipeline.addLast(httpServiceHandler)
    }

}