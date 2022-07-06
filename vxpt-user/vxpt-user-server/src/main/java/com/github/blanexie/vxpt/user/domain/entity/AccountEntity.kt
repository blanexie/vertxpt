package com.github.blanexie.vxpt.user.domain.entity


import com.github.blanexie.vxpt.user.support.jpa.repository.AccountRepository
import java.time.LocalDateTime

/**
 * 领域不应该直接依赖任何的外部内容，

 *
 */
class AccountEntity(
    val id: Int,
    val userId: Int,
    var level: Int = 1, //等级
    var points: Int = 0,//积分
    var invitationCount: Int = 0,  //当前用户拥有的邀请函数量,
    var completeCount: Int = 0, //完种数
    var activeCount: Int = 0, //活动种子数
    var download: Long = 0, //真实下载量, 单位byte
    var upload: Long = 0, //真实上传量, 单位byte
    val createTime: LocalDateTime = LocalDateTime.now(), //
) {

    fun addDownload(count: Long) {
        this.download += count
    }
    fun addUpload(count: Long) {
        this.upload += count
    }

    fun addActiveCount(count: Int) {
        this.activeCount += count
    }
    fun addInvitationCount(count: Int) {
        this.invitationCount += count
    }
    fun addPoints(count: Int) {
        this.points += count
    }

    fun addLevel(count: Int) {
        this.level += count
    }
    fun addCompleteCount(count: Int) {
        this.completeCount += count
    }


}