package com.github.blanexie.vxpt.user.domain.entity


import com.github.blanexie.vxpt.user.support.jpa.repository.AccountRepository
import java.time.LocalDateTime

/**
 * 领域不应该直接依赖任何的外部内容，
 * 这里依赖了 DO数据库对象
 *
 */
class AccountEntity(
    val accountId: Int,
    val userId: Int,
    var level: Int = 1, //等级
    var points: Int = 0,//积分
    var invitationCount: Int = 0,  //当前用户拥有的邀请函数量,
    var completeCount: Int = 0, //完种数
    var activeCount: Int = 0, //活动种子数
    var download: Long = 0, //真实下载量, 单位byte
    var upload: Long = 0, //真实上传量, 单位byte
    var updateTime: LocalDateTime = LocalDateTime.now(), //
    val createTime: LocalDateTime = LocalDateTime.now(), //

) {





}