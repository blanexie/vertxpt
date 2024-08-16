package com.github.blanexie.vxpt.bbs.user.meta.entity

import com.baomidou.mybatisplus.annotation.*
import java.time.LocalDateTime

@TableName("user")
class UserDO(
    @TableId(type = IdType.AUTO)
    var id: Long?,
    var email: String,
    var nickName: String,
    var password: String,
    var coverImg: String?,
    var role: String,
    var sex: Int,
    var status: Int = 0,
    var createTime: LocalDateTime = LocalDateTime.now(),
    var updateTime: LocalDateTime = LocalDateTime.now(),
) {
}