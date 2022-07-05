package com.github.blanexie.vxpt.user.domain.service

import com.github.blanexie.vxpt.user.api.dto.PublishData
import com.github.blanexie.vxpt.user.api.dto.UserDTO
import com.github.blanexie.vxpt.user.domain.entity.AccountDomain
import com.github.blanexie.vxpt.user.domain.entity.UserDomain
import com.github.blanexie.vxpt.user.support.jpa.repository.AccountRepository
import com.github.blanexie.vxpt.user.support.jpa.repository.UserRepository

/**
 * 登录和数据上报的领域服务
 */
class LoginAndPublishDataService(private val userRepository: UserRepository, private val accountRepository: AccountRepository) {

    /**
     * 校验登录, 登录成功了才会返回UserDTO
     *
     */
    fun login(email: String, pwdSecret: String, timeStamp: Long): UserDTO? {
        val userDO = userRepository.findFirstByEmail(email) ?: return null
        val userDomain = UserDomain(userDO)
        return if (userDomain.checkPwd(pwdSecret, timeStamp)) userDomain.toDTO() else null
    }

    /**
     * 上报统计数据
     */
    fun publishData(publishData: PublishData) {
        val accountDO = accountRepository.findFirstByUserId(publishData.userId) ?: return
        val accountDomain = AccountDomain(accountDO)
        accountDomain.publish(publishData, accountRepository)
    }

}