package com.github.blanexie.vxpt.bbs.post.controller

import cn.dev33.satoken.stp.StpUtil
import cn.dev33.satoken.util.SaResult
import com.github.blanexie.vxpt.api.user.feign.UserRpc
import com.github.blanexie.vxpt.api.user.dto.RegisterUserDTO
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource


@RestController
@RequestMapping("/api/user")
class UserController(@Resource val userRpc: UserRpc) {

    val log = LoggerFactory.getLogger(UserController::class.java)

    @PostMapping("/login")
    fun doLogin(@RequestBody userDTO: RegisterUserDTO): SaResult {
        log.info("{},{}", userDTO.nickName, userDTO.password)
        val userId = userRpc.login(userDTO.nickName, userDTO.password)
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
    fun register(@RequestBody registerUserDTO: RegisterUserDTO): SaResult {
        val userId = userRpc.register(registerUserDTO)
        StpUtil.login(userId)
        return SaResult.ok()
    }
}