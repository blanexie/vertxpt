package com.github.blaenxie.vxpt.bbs.controller

import com.hellokaton.blade.annotation.Path
import com.hellokaton.blade.annotation.request.Form
import com.hellokaton.blade.annotation.request.PathParam
import com.hellokaton.blade.annotation.route.DELETE
import com.hellokaton.blade.annotation.route.GET
import com.hellokaton.blade.annotation.route.POST
import com.hellokaton.blade.annotation.route.PUT
import com.hellokaton.blade.mvc.ui.ResponseType

@Path
class IndexController {

    @GET("/", responseType = ResponseType.TEXT)
    fun index(): String {
        return "index.html"
    }

    @POST("/save")
    fun saveUser(@Form username: String) {
        println("username:$username")
    }

    @PUT("/update")
    fun updateUser(@Form username: String) {
        println("username:$username")
    }

    @DELETE("/delete/:uid")
    fun deleteUser(@PathParam uid: Int) {
        println("delete user:$uid")
    }
}