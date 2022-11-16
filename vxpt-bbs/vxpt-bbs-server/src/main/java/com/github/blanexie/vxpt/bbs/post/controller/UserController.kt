package com.github.blanexie.vxpt.bbs.post.controller

import cn.dev33.satoken.stp.StpUtil
import cn.dev33.satoken.util.SaResult
import com.github.blanexie.vxpt.api.user.feign.UserRpc
import com.github.blanexie.vxpt.api.user.dto.RegisterUserDTO
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource

@RestController
@RequestMapping("/api/user")
class UserController(@Resource val userRpc: UserRpc) {

    @PostMapping("/login")
    fun doLogin(userDTO: RegisterUserDTO): SaResult {
        val userId = userRpc.login(userDTO.email, userDTO.pwd)
        if (userId != null) {
            StpUtil.login(userId)
            return SaResult.ok()
        }
        return SaResult.get(503, "账号或者密码错误", null)
    }

    @GetMapping("/isLogin")
    fun isLogin(): SaResult {
        return SaResult.data(StpUtil.isLogin())
    }

    @GetMapping("/logout")
    fun logout(): SaResult {
        StpUtil.logout()
        return SaResult.ok()
    }

    @PostMapping("/register")
    fun register(@RequestBody  registerUserDTO: RegisterUserDTO): SaResult {
        val userDTO = userRpc.register(registerUserDTO)
        StpUtil.login(userDTO.id)
        return SaResult.ok()
    }
}