package com.github.blanexie.vxpt.bbs.controller

import cn.dev33.satoken.stp.StpInterface
import cn.dev33.satoken.stp.StpUtil
import com.github.blanexie.vxpt.bbs.util.Response
import com.github.blanexie.vxpt.user.api.UserRpc
import com.github.blanexie.vxpt.user.api.dto.RegisterUserDTO
import com.github.blanexie.vxpt.user.api.dto.UserDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

@RestController
@RequestMapping("/api/user/")
class UserController(@Resource val userRpc: UserRpc) {

    @PostMapping("/login")
    fun doLogin(userDTO: RegisterUserDTO): Response {
        val userId = userRpc.login(userDTO.email, userDTO.pwd)
        if (userId != null) {
            StpUtil.login(userId)
            return Response.ok()
        }
        return return Response.fail(503)
    }

    @GetMapping("/isLogin")
    fun isLogin(): Response {
        return Response.ok(StpUtil.isLogin())
    }

    @GetMapping("/logout")
    fun logout(): Response {
        return Response.ok(StpUtil.logout())
    }

}