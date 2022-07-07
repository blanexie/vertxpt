package com.github.blanexie.vxpt.user.domain.service

import com.github.blanexie.vxpt.user.domain.entity.AccountEntity
import com.github.blanexie.vxpt.user.domain.entity.UserEntity
import com.github.blanexie.vxpt.user.domain.factory.AccountEntityFactory
import com.github.blanexie.vxpt.user.domain.factory.InvitationEntityFactory
import com.github.blanexie.vxpt.user.domain.factory.UserEntityFactory

/**
 * 登录和数据上报的领域服务
 */
class LoginAndPublishDataService(
    private val userEntityFactory: UserEntityFactory,
    private val accountEntityFactory: AccountEntityFactory,
    private val invitationEntityFactory: InvitationEntityFactory
) {

    /**
     * 校验登录,
     */
    fun login(email: String, pwdSecret: String, timeStamp: Long): Boolean {
        val userEntity = userEntityFactory.findByEmail(email)
        return userEntity.checkPwd(pwdSecret, timeStamp)
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
        val userId = userEntityFactory.nextSeqId()
        //检查邀请函
        val invitationDO = invitationEntityFactory.findByCode(code)
        invitationDO.use(userId)
        // 创建用户
        val userEntity = UserEntity(userId, nickName, email, pwd, sex)
        // 创建账户
        val accountId = accountEntityFactory.nextSeqId()
        val accountEntity = AccountEntity(accountId, userId)
        userEntityFactory.save(userEntity)
        invitationEntityFactory.save(invitationDO)
        accountEntityFactory.save(accountEntity)
        return userId
    }

    /**
     * 上报数据
     */
    fun addAccountData(
        userId: Int,
        addDownload: Long = 0,
        upload: Long = 0,
        activeCount: Int = 0,
        completeCount: Int = 0
    ) {
        val accountEntity = accountEntityFactory.findByUserId(userId)
        accountEntity.addActiveCount(activeCount)
        accountEntity.addCompleteCount(completeCount)
        accountEntity.addDownload(addDownload)
        accountEntity.addUpload(upload)
        accountEntityFactory.save(accountEntity)
    }


}