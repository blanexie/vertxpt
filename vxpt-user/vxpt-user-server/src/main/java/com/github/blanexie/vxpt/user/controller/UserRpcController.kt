package com.github.blanexie.vxpt.user.controller

import com.github.blanexie.vxpt.api.user.feign.UserRpc
import com.github.blanexie.vxpt.api.user.dto.RegisterUserDTO
import com.github.blanexie.vxpt.api.user.dto.RoleDTO
import com.github.blanexie.vxpt.api.user.dto.UserDTO
import com.github.blanexie.vxpt.user.service.InvitationService
import com.github.blanexie.vxpt.user.service.RoleService
import com.github.blanexie.vxpt.user.service.UserService
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

@RestController
class UserRpcController(
    @Resource
    val userService: UserService,
    @Resource
    val roleService: RoleService,
    @Resource
    val invitationService: InvitationService
) : UserRpc {


    override fun userInfo(userId: Int): UserDTO? {
        val userDO = userService.findById(userId)
        val roles = userDO!!.roles.mapNotNull { roleService.findRole(it) }
            .map {
                RoleDTO(
                    it.id, it.name, it.code, it.permissionCodes
                )
            }
        return UserDTO(
            userId, userDO.nickName, userDO.email, userDO.sex, roles
        )
    }

    override fun login(email: String, pwdSecret: String): Int? {
        return userService.login(email, pwdSecret)?.id
    }

    override fun register(registerUserDTO: RegisterUserDTO): Int {
        val nextUserId = userService.nextUserId()
        val invitationId = invitationService.use(registerUserDTO.code, nextUserId)
        registerUserDTO.userId = nextUserId
        registerUserDTO.invitationId = invitationId
        return userService.register(
            registerUserDTO
        )
    }

}