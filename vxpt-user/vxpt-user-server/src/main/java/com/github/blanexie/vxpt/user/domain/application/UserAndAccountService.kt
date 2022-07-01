package com.github.blanexie.vxpt.user.domain.application

import com.github.blanexie.vxpt.user.api.dto.PublishData
import com.github.blanexie.vxpt.user.api.dto.UserDTO
import com.github.blanexie.vxpt.user.domain.AccountDomain
import com.github.blanexie.vxpt.user.domain.UserDomain
import com.github.blanexie.vxpt.user.domain.repository.AccountRepository
import com.github.blanexie.vxpt.user.domain.repository.UserRepository
import org.springframework.stereotype.Service
import javax.annotation.Resource


@Service
class UserAndAccountService {

    @Resource
    lateinit var userRepository: UserRepository;

    @Resource
    lateinit var accountRepository: AccountRepository

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