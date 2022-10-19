package com.github.blanexie.vxpt.user.service

import cn.hutool.core.util.StrUtil
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
    fun login(email: String, pwdSecret: String): Boolean {
        val userDO = userRepository.findByEmail(email)
        return if (userDO == null) false else userDO.checkPwd(pwdSecret)
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

    /**
     * 注册, 返回的是userId
     */
    fun register(
        code: String,
        email: String,
        pwd: String,
        nickName: String,
        sex: Int
    ): Int {
        val userId = userRepository.nextSeqId()
        //检查邀请函
        val invitationDO = invitationRepository.findByCodeAndStatus(code, 0) ?: throw  Error("请输入正确的邀请码")
        val msg = invitationDO.use(userId)
        if (StrUtil.isBlank(msg)) {
            throw  Error(msg);
        }

        // 创建用户
        val userDO = UserDO(
            userId, nickName, email, pwd, sex, listOf("normal"),
            invitationDO.id, LocalDateTime.now(),
            LocalDateTime.now(), 0
        )

        userRepository.save(userDO)
        invitationRepository.save(invitationDO)
        return userId
    }

}