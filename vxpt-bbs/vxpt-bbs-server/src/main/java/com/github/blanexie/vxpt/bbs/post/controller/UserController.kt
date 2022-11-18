package com.github.blanexie.vxpt.bbs.post.controller

import cn.dev33.satoken.stp.StpUtil
import cn.dev33.satoken.util.SaResult
import com.github.blanexie.vxpt.api.user.dto.LoginUserDTO
import com.github.blanexie.vxpt.api.user.feign.UserRpc
import com.github.blanexie.vxpt.api.user.dto.RegisterUserDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource


@RestController
@RequestMapping("/api/user")
class UserController(@Resource val userRpc: UserRpc) {

    val log: Logger = LoggerFactory.getLogger(UserController::class.java)!!

    @GetMapping("/isLogin")
    fun isLogin(): SaResult {
        return SaResult.data(StpUtil.isLogin())
    }

    @GetMapping("/info")
    fun getUserInfo(): SaResult {
        val userId = StpUtil.getLoginIdAsInt()
        var userInfo = userRpc.userInfo(userId)
        return SaResult.data(userInfo)
    }

    @GetMapping("/logout")
    fun logout(): SaResult {
        StpUtil.logout()
        return SaResult.ok()
    }


}