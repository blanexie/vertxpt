package com.github.blanexie.vxpt.bbs.post.controller

import cn.dev33.satoken.stp.StpUtil
import cn.dev33.satoken.util.SaResult
import cn.hutool.core.date.DateUtil
import com.github.blanexie.vxpt.api.user.dto.LoginUserDTO
import com.github.blanexie.vxpt.api.user.dto.RegisterUserDTO
import com.github.blanexie.vxpt.api.user.feign.UserRpc
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import java.util.*
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
        return if (r.data != null) {
            StpUtil.login(r.data)
            SaResult.ok()
        } else {
            SaResult.error(r.msg)
        }
    }


    /**
     * 重置密码
     */
    @GetMapping("/reset/password")
    fun sendResetPwdEmail(
        @RequestParam email: String,
        @RequestParam token: String,
        @RequestParam expireTime: Long,
        @RequestParam password: String
    ): SaResult {
        val r = userRpc.checkTokenAndResetPwd(email, token, expireTime, password)
        if (r.msg != null) {
            return SaResult.error(r.msg)
        }
        return SaResult.ok()
    }

    /**
     * 发送重置密码邮件
     */
    @GetMapping("/send/reset/email")
    fun sendResetPwdEmail(email: String): SaResult {
        //找到对应的用户
        val expireTime = DateUtil.offsetHour(Date(), 6).time
        val r = userRpc.createResetPwdToken(email, expireTime)
        if (r.msg != null) {
            return SaResult.error(r.msg)
        }
        val map = r.data as Map<String, Any>

        // TODO: 2022/11/24 send Email 应该限制用户的频繁调用
        return SaResult.data("找回密码邮件已经发送到注册邮箱中了，请进入邮箱查看。")
    }


}