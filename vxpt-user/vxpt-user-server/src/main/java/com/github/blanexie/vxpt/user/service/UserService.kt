package com.github.blanexie.vxpt.user.service

import cn.hutool.core.util.StrUtil
import com.github.blanexie.vxpt.user.api.dto.RegisterUserDTO
import com.github.blanexie.vxpt.user.entity.UserDO
import com.github.blanexie.vxpt.user.repository.InvitationRepository
import com.github.blanexie.vxpt.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * 登录和数据上报的领域服务
 */
@Component
class UserService(
    private val userRepository: UserRepository,
    private val invitationRepository: InvitationRepository
) {

    /**
     * 校验登录,
     */
    fun login(email: String, pwdSecret: String): UserDO? {
        val userDO = userRepository.findByEmail(email)
        return if (userDO != null && userDO.checkPwd(pwdSecret)) {
            userDO;
        } else {
            null
        }
    }


    fun findById(userId: Int): UserDO? {
        return userRepository.findByIdOrNull(userId)
    }

    /**
     *  增加角色
     */
    fun addRole(userId: Int, roleCode: String): Boolean {
        val userDO = userRepository.findByIdOrNull(userId)
        return if (userDO == null) {
            false
        } else {
            userDO.addRole(roleCode)
            true
        }
    }

    /**
     *  减少角色
     */
    fun removeRole(userId: Int, roleCode: String): Boolean {
        val userDO = userRepository.findByIdOrNull(userId)
        return if (userDO == null) {
            false
        } else {
            userDO.remove(roleCode)
            true
        }
    }


    fun nextUserId(): Int {
        return userRepository.nextSeqId()
    }

    /**
     * 注册, 返回的是userId
     */
    fun register(
        registerUserDTO: RegisterUserDTO
    ): Int {
        // 创建用户
        val userDO = UserDO(
            registerUserDTO.userId,
            registerUserDTO.nickName,
            registerUserDTO.email,
            registerUserDTO.pwd,
            registerUserDTO.sex,
            listOf("normal"),
            registerUserDTO.invitationId,
            LocalDateTime.now(),
            LocalDateTime.now(), 0
        )
        userRepository.save(userDO)
        return userDO.id
    }

}