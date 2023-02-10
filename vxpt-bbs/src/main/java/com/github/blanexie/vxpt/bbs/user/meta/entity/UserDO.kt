package com.github.blanexie.vxpt.bbs.user.meta.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
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
    var status: Int,
    var createTime: LocalDateTime,
    var updateTime: LocalDateTime,
) {
}