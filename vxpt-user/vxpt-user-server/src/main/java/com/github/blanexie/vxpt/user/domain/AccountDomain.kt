package com.github.blanexie.vxpt.user.domain

import com.github.blanexie.vxpt.user.api.dto.PublishData
import com.github.blanexie.vxpt.user.domain.entity.AccountDO
import com.github.blanexie.vxpt.user.domain.adapter.repository.AccountRepository

/**
 * 领域不应该直接依赖任何的外部内容，
 * 这里依赖了 DO数据库对象
 *
 */
class AccountDomain(val accountDO: AccountDO) {

    val accountId: Int = accountDO.id!!
    val userId: Int = accountDO.userId!!

    /**
     * 上报数据
     */
    fun publish(publishData: PublishData, accountRepository: AccountRepository) {
        this.accountDO.activeTorrentNum = publishData.activeTorrentNum
        this.accountDO.uploadTorrentNum = publishData.uploadTorrentNum
        this.accountDO.download += publishData.addDownload
        this.accountDO.points += publishData.addPoints
        this.accountDO.upload += publishData.addUpload
        accountRepository.save(accountDO)
    }


}