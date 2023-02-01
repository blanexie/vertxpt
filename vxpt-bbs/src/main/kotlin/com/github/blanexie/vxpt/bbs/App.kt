package com.github.blanexie.vxpt.bbs

import com.jfinal.server.undertow.UndertowServer

/**
 * 注意：用于启动的 main 方法可以在任意 java 类中创建，在此仅为方便演示
 * 才将 main 方法放在了 DemoConfig 中
 *
 * 开发项目时，建议新建一个 App.java 或者 Start.java 这样的专用
 * 启动入口类放置用于启动的 main 方法
 */
fun main(args: Array<String>) {
    UndertowServer.start(DemoConfig::class.java, 8880, true)
}