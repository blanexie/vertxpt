package com.github.blanexie.vxpt.user.controller

import com.github.blanexie.vxpt.api.user.dto.*
import com.github.blanexie.vxpt.api.user.feign.UserRpc
import com.github.blanexie.vxpt.user.entity.UserDO
import com.github.blanexie.vxpt.user.service.InvitationService
import com.github.blanexie.vxpt.user.service.RoleService
import com.github.blanexie.vxpt.user.service.UserService
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import javax.annotation.Resource

@RestController
class UserRpcController(
    val userService: UserService, val roleService: RoleService, val invitationService: InvitationService
) : UserRpc {


    override fun userInfo(userId: Int): UserDTO? {
        val userDO = userService.findById(userId)
        val roles = userDO!!.roles.mapNotNull { roleService.findRole(it) }
            .map {
                RoleDTO(it.id, it.name, it.code, it.permissionCodes)
            }
        return UserDTO(
            userId, userDO.nickName, userDO.email, userDO.sex, roles
        )
    }

    override fun login(loginUserDTO: LoginUserDTO): Int? {
        return userService.login(loginUserDTO.nickName!!, loginUserDTO.password, loginUserDTO.loginTime)?.id
    }

    override fun register(registerUserDTO: RegisterUserDTO): R {
        //检查昵称是否可用
        userService.findByNickName(registerUserDTO.nickName) ?: return R(msg = "用户昵称已存在")

        //检查邀请还是否可用
        val invitationDO = invitationService.findByCode(registerUserDTO.code) ?: return R(msg = "邀请码不存在")
        val error = invitationDO.check(registerUserDTO.email)
        if (error != null) {
            return R(msg = error)
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

        val save = userService.save(userDO)
        return R(data = save.id)
    }

}