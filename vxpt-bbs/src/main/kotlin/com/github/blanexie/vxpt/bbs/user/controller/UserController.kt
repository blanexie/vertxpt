package com.github.blanexie.vxpt.bbs.user.controller

import com.github.blanexie.vxpt.bbs.util.WebResp
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController {

    @GetMapping("/login")
    fun login(): WebResp {
        return WebResp.success()
    }

    @GetMapping("/logout")
    fun logout(): WebResp {
        return WebResp.success()
    }

    @GetMapping("/register")
    fun register(): WebResp {
        return WebResp.success()
    }

}
