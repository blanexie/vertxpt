package com.github.blanexie.vxpt.bbs.user.controller

import com.github.blanexie.vxpt.bbs.user.dao.UserMapper
import com.github.blanexie.vxpt.bbs.util.WebResp
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

@RestController
@RequestMapping("/api/user")
class UserController {

    @Resource
    lateinit var userMapper: UserMapper

    @GetMapping("/login")
    fun login(): WebResp {
        var userDO = userMapper.selectById(1)
        return WebResp.success(userDO)
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
