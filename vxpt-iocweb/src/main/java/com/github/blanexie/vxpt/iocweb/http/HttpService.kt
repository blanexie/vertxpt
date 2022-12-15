package com.github.blanexie.vxpt.iocweb

import cn.hutool.core.thread.ThreadUtil
import com.github.blanexie.vxpt.ioc.process.AppCompleteRunner
import com.github.blanexie.vxpt.ioc.annotation.Component
import com.github.blanexie.vxpt.ioc.annotation.Inject
import com.github.blanexie.vxpt.iocweb.http.HttpServiceInitializer
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.Channel
import io.netty.channel.ChannelOption
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

@Component
class HttpService : AppCompleteRunner {

    private val log: Logger = LoggerFactory.getLogger(HttpService::class.java)

    @Inject
    lateinit var properties: Properties

    @Inject
    lateinit var httpServiceInitializer: HttpServiceInitializer

    override fun process() {
        ThreadUtil.execute {
            val port: String = properties.getProperty("http.server.port", "8016")
            val bossGroupNum: String = properties.getProperty("http.bossGroup.num", "1")

            val bossGroup: EventLoopGroup = NioEventLoopGroup(bossGroupNum.toInt())
            val workerGroup: EventLoopGroup = NioEventLoopGroup()
            try {
                val b = ServerBootstrap()
                b.option(ChannelOption.SO_BACKLOG, 1024)
                b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel::class.java)
                    .handler(LoggingHandler(LogLevel.INFO))
                    .childHandler(httpServiceInitializer)
                val ch: Channel = b.bind(port.toInt()).sync().channel()
                log.info("Open your web browser and navigate to http://127.0.0.1:$port/")
                ch.closeFuture().sync()
            } finally {
                bossGroup.shutdownGracefully()
                workerGroup.shutdownGracefully()
            }
        }
    }
}

