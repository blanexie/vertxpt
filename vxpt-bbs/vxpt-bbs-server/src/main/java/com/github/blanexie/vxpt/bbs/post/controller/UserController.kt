package com.github.blanexie.vxpt.bbs.post.controller

import cn.dev33.satoken.stp.StpUtil
import cn.dev33.satoken.util.SaResult
import com.github.blanexie.vxpt.api.user.feign.UserRpc
import com.github.blanexie.vxpt.api.user.dto.RegisterUserDTO
import com.github.blanexie.vxpt.bbs.post.dto.LoginUserDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource


@RestController
@RequestMapping("/api/user")
class UserController(@Resource val userRpc: UserRpc) {

    val log: Logger = LoggerFactory.getLogger(UserController::class.java)!!

    @PostMapping("/login")
    fun doLogin(@RequestBody loginUserDTO: LoginUserDTO): SaResult {
        log.info("doLogin  {},{},{}", loginUserDTO.nickName, loginUserDTO.password, loginUserDTO.loginTime)
        val userId = userRpc.login(loginUserDTO.nickName, loginUserDTO.password, loginUserDTO.loginTime)
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