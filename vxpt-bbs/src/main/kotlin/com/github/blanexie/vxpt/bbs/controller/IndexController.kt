package com.github.blanexie.vxpt.bbs.controller

import com.jfinal.core.Controller
import com.jfinal.core.Path


@Path("/hello")
class HelloController : Controller() {

    fun index() {
        renderText("Hello JFinal World.")
    }

}