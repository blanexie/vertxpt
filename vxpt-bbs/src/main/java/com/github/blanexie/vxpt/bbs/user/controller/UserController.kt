package com.github.blanexie.vxpt.bbs.user.controller

import com.github.blanexie.vxpt.bbs.user.dao.UserMapper
import com.github.blanexie.vxpt.bbs.user.meta.entity.UserDO
import com.github.blanexie.vxpt.bbs.util.WebResp
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import javax.annotation.Resource

@RestController
@RequestMapping("/api/user")
class UserController {

    @Resource
    lateinit var userMapper: UserMapper

    @GetMapping("/login")
    fun login(): WebResp {
        var userDO = UserDO(
            12, "12@vxpt", "12vxpt", "sagsdg", "https://21gsfsa", "user", 1, 1, LocalDateTime.now(), LocalDateTime.now()
        )
        userMapper.insert(userDO)
        return WebResp.success(userDO)
    }

    @GetMapping("/logout")
    fun logout(): WebResp {
        var selectById = userMapper.selectById(12)
        return WebResp.success(selectById)
    }

    @GetMapping("/register")
    fun register(): WebResp {
        return WebResp.success()
    }

}
