package com.github.blanexie.vxpt.user.controller

import cn.hutool.core.date.DateUtil
import cn.hutool.crypto.digest.DigestUtil
import com.github.blanexie.vxpt.api.user.dto.*
import com.github.blanexie.vxpt.api.user.feign.UserRpc
import com.github.blanexie.vxpt.user.entity.UserDO
import com.github.blanexie.vxpt.user.service.InvitationService
import com.github.blanexie.vxpt.user.service.RoleService
import com.github.blanexie.vxpt.user.service.UserService
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.util.*

@RestController
class UserRpcController(
    val userService: UserService, val roleService: RoleService, val invitationService: InvitationService
) : UserRpc {


    override fun userInfo(userId: Int): UserDTO? {
        val userDO = userService.findById(userId) ?: return null
        val roles = userDO.roles.mapNotNull { roleService.findRole(it) }
            .map {
                RoleDTO(it.id, it.name, it.code, it.permissionCodes)
            }
        return UserDTO(
            userId, userDO.nickName, userDO.email, userDO.sex, roles
        )
    }

    override fun userInfoByEmail(email: String): UserDTO? {
        val userDO = userService.findByEmail(email) ?: return null
        val roles = userDO.roles.mapNotNull { roleService.findRole(it) }
            .map {
                RoleDTO(it.id, it.name, it.code, it.permissionCodes)
            }
        return UserDTO(
            userDO.id, userDO.nickName, userDO.email, userDO.sex, roles
        )
    }

    override fun createResetPwdToken(email: String, expireTime: Long): Resp {
        val userDO = userService.findByEmail(email) ?: return Resp(msg = "请传入正确的邮箱")
        val id = userDO.id
        val pwd = userDO.pwd
        val token = DigestUtil.sha256Hex("$id$pwd$expireTime")
        return Resp(data = token)
    }

    override fun checkTokenAndResetPwd(email: String, token: String, expireTime: Long, newPassword: String): Resp {
        val userDO = userService.findByEmail(email) ?: return Resp(msg = "请传入正确的邮箱")
        //校验是否过期
        if (expireTime < System.currentTimeMillis()) {
            return Resp(msg = "已经过期，重置失败")
        }
        val id = userDO.id
        val pwd = userDO.pwd
        val expireTime = DateUtil.offsetHour(Date(), 4).time
        val sha256Hex = DigestUtil.sha256Hex("$id$pwd$expireTime")
        if (sha256Hex != token) {
            return Resp(msg = "验证错误，重置失败")
        }
        return if (pwd == newPassword) {
            Resp(msg = "验证错误，重置失败")
        } else {
            userDO.pwd = newPassword
            userService.save(userDO)
            Resp(true)
        }
    }

    override fun login(loginUserDTO: LoginUserDTO): Int? {
        return userService.login(loginUserDTO.nickName!!, loginUserDTO.password, loginUserDTO.loginTime)?.id
    }

    override fun register(registerUserDTO: RegisterUserDTO): Resp {
        //检查昵称是否可用
        userService.findByNickName(registerUserDTO.nickName) ?: return Resp(msg = "用户昵称已存在")

        //检查邀请还是否可用
        val invitationDO = invitationService.findByCode(registerUserDTO.code) ?: return Resp(msg = "邀请码不存在")
        val error = invitationDO.check(registerUserDTO.email)
        if (error != null) {
            return Resp(msg = error)
        }
        //使用邀请码
        val nextUserId = userService.nextUserId()
        invitationDO.use(nextUserId)
        val invitationId = invitationService.save(invitationDO)
        //新增用户
        val userDO = UserDO(
            nextUserId,
            registerUserDTO.nickName,
            registerUserDTO.email,
            registerUserDTO.password,
            registerUserDTO.sex,
            listOf("normal"),
            invitationId,
            LocalDateTime.now(),
            LocalDateTime.now(),
            0
        )

        val id = userService.save(userDO)
        return Resp(data = id)
    }

}