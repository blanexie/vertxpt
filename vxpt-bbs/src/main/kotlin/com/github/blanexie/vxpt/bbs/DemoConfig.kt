package com.github.blanexie.vxpt.bbs

import com.jfinal.config.*
import com.jfinal.server.undertow.UndertowServer
import com.jfinal.template.Engine

class DemoConfig : JFinalConfig() {


    override fun configConstant(me: Constants) {
        me.setDevMode(true)
    }

    override fun configRoute(me: Routes) {
        // jfinal 4.9.03 版新增了路由扫描功能，不必手动添加路由
        // me.add("/hello", HelloController.class);

        // 使用路由扫描，参数 "demo." 表示只扫描 demo 包及其子包下的路由
        me.scan("com.github.blanexie.vxpt.bbs.")
    }

    override fun configEngine(me: Engine?) {}
    override fun configPlugin(me: Plugins?) {}
    override fun configInterceptor(me: Interceptors?) {}
    override fun configHandler(me: Handlers?) {}

}