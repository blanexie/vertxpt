package com.github.blanexie.vxpt.bbs.user.controller

import com.github.blanexie.vxpt.bbs.user.dao.RoleMapper
import com.github.blanexie.vxpt.bbs.user.dao.UserMapper
import com.github.blanexie.vxpt.bbs.user.meta.entity.RoleDO
import com.github.blanexie.vxpt.bbs.util.spring.WebResp
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

@RestController
@RequestMapping("/api/user")
class UserController {

    @Resource
    lateinit var userMapper: UserMapper
    @Resource
    lateinit var roleMapper: RoleMapper

    @GetMapping("/login")
    fun login(): WebResp {
         val roleDO= RoleDO(2,"admin","管理员",
             listOf("增","删"),  listOf("增","删"))
        roleMapper.insert(roleDO)
        return WebResp.success(roleDO)
    }

    @GetMapping("/logout")
    fun logout(): WebResp {
        var selectById = roleMapper.selectById(1)
        return WebResp.success(selectById)
    }

    @GetMapping("/register")
    fun register(): WebResp {
        return WebResp.success()
    }

}
