package com.github.blanexie.vxpt.user.domain.service

import com.github.blanexie.vxpt.user.domain.factory.AccountEntityFactory

class AccountService(
    private val accountEntityFactory: AccountEntityFactory,
) {

    /**
     * 上报数据
     */
    fun addAccountData(
        userId: Int,
        addDownload: Long = 0,
        addUpload: Long = 0,
        addActiveCount: Int = 0,
        addCompleteCount: Int = 0
    ) {
        val accountEntity = accountEntityFactory.findByUserId(userId)
        accountEntity.addActiveCount(addActiveCount)
        accountEntity.addCompleteCount(addCompleteCount)
        accountEntity.addDownload(addDownload)
        accountEntity.addUpload(addUpload)
        accountEntityFactory.save(accountEntity)
    }
}