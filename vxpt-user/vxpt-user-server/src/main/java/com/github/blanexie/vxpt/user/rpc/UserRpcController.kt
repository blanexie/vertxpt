package com.github.blanexie.vxpt.user.rpc

import cn.hutool.crypto.SecureUtil
import com.github.blanexie.vxpt.user.api.UserRpc
import com.github.blanexie.vxpt.user.api.dto.RoleDTO
import com.github.blanexie.vxpt.user.api.dto.UserDTO
import com.github.blanexie.vxpt.user.service.RoleService
import com.github.blanexie.vxpt.user.service.UserService
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

@RestController
class UserRpcController : UserRpc {

    @Resource
    lateinit var userService: UserService

    @Resource
    lateinit var roleService: RoleService

    @Value("\${token.aes.key}")
    lateinit var tokenSecret: String

    @Value("\${token.expireSecond}")
    lateinit var expireSecond: String

    fun getExpireSecond(): Long {
        return expireSecond.toLong()
    }

    override fun userInfo(token: String): UserDTO? {
        val decrypt = SecureUtil.aes(tokenSecret.toByteArray()).decrypt(token)
        val split = String(decrypt).split("|")
        val useId = split[0].toInt()
        val createTime = split[1].toLong()

        if (createTime + getExpireSecond() * 1000 < System.currentTimeMillis()) {
            return null
        } else {
            val userDO = userService.findById(useId) ?: return null
            val roles = userDO.roles.mapNotNull { roleService.findRole(it) }
                .map {
                    RoleDTO(it.id, it.name, it.code, it.permissionCodes)
                }
            return UserDTO(useId, userDO.nickName, userDO.email, userDO.sex, roles)
        }

    }
}