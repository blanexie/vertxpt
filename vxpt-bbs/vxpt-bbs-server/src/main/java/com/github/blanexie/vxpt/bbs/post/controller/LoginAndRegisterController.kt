package com.github.blanexie.vxpt.bbs.post.controller

import cn.dev33.satoken.stp.StpUtil
import cn.dev33.satoken.util.SaResult
import com.github.blanexie.vxpt.api.user.dto.LoginUserDTO
import com.github.blanexie.vxpt.api.user.dto.RegisterUserDTO
import com.github.blanexie.vxpt.api.user.feign.UserRpc
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

@RestController
@RequestMapping("/api/user/lr")
class LoginAndRegisterController(@Resource val userRpc: UserRpc) {
    val log: Logger = LoggerFactory.getLogger(LoginAndRegisterController::class.java)!!

    @PostMapping("/login")
    fun doLogin(@RequestBody loginUserDTO: LoginUserDTO): SaResult {
        log.info("doLogin  {},{},{}", loginUserDTO.nickName, loginUserDTO.password, loginUserDTO.loginTime)
        val userId = userRpc.login(loginUserDTO)
        if (userId != null) {
            StpUtil.login(userId)
            return SaResult.ok()
        }
        return SaResult.get(503, "账号或者密码错误", null)
    }

    @PostMapping("/register")
    fun register(@RequestBody registerUserDTO: RegisterUserDTO): SaResult {
        val r = userRpc.register(registerUserDTO)
        if (r.data != null) {
            StpUtil.login(r.data)
            return SaResult.ok()
        } else {
            return SaResult.error(r.msg)
        }
    }
}