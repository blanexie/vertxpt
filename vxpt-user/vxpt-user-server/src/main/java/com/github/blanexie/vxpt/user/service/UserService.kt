package com.github.blanexie.vxpt.user.service

import com.github.blanexie.vxpt.api.user.dto.RegisterUserDTO
import com.github.blanexie.vxpt.user.entity.UserDO
import com.github.blanexie.vxpt.user.repository.InvitationRepository
import com.github.blanexie.vxpt.user.repository.UserRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * 登录和数据上报的领域服务
 */
@Component
class UserService(
    private val userRepository: UserRepository
) {

    /**
     * 校验登录,
     */
    fun login(nickName: String, pwdSecret: String, longTime: Long): UserDO? {
        val userDO = userRepository.findByNickName(nickName)
        return if (userDO != null && userDO.checkPwd(pwdSecret, longTime)) {
            userDO
        } else {
            null
        }
    }


    fun findById(userId: Int): UserDO? {
        return userRepository.findById(userId).orElse(null)
    }

    /**
     *  增加角色
     */
    fun addRole(userId: Int, roleCode: String) {
        val userDO = userRepository.findById(userId)
        userDO.map {
            it.addRole(roleCode)
        }
    }

    /**
     *  减少角色
     */
    fun removeRole(userId: Int, roleCode: String) {
        val userDO = userRepository.findById(userId)
        userDO.map {
            it.remove(roleCode)
        }
    }


    fun nextUserId(): Int {
        return userRepository.nextSeqId()
    }

    /**
     * 注册, 返回的是userId
     */
    fun save(userDO: UserDO): Int {
        userRepository.saveAndFlush(userDO)
        return userDO.id
    }

}