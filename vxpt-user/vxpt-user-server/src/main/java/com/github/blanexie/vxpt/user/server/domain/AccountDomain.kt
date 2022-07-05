package com.github.blanexie.vxpt.user.domain

import com.fasterxml.jackson.annotation.JsonFormat
import com.github.blanexie.vxpt.user.api.dto.PublishData
import com.github.blanexie.vxpt.user.domain.adapter.repository.AccountRepository
import com.github.blanexie.vxpt.user.domain.entity.AccountDO
import java.time.LocalDateTime
import javax.persistence.Column

/**
 * 领域不应该直接依赖任何的外部内容，
 * 这里依赖了 DO数据库对象
 *
 */
class AccountDomain(val accountDO: AccountDO) {
    val accountId: Int = accountDO.id!!
    val userId: Int = accountDO.userId!!

    /**
     * 等级
     */
    private val level: String? = null
    /**
     * 积分
     */
    private val points: Int? = null
    /**
     * 当前用户拥有的邀请函数量,
     */
    private val invitationNum: Int? = null
    /**
     * 完种数
     */
    private val completeCount: Int? = null
    /**
     * 活动种子数
     */
    private val activeCount: Int? = null
    /**
     * 真实下载量
     */
    private val download: Long? = null
    /**
     * 真实上传量
     */
    private val upload: Long? = null



    private val createTime: LocalDateTime? = null
    private val updateTime: LocalDateTime? = null



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



    /**
     * 上报关联种子数
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