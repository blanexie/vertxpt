package com.github.blanexie.vxpt.bbs.user.meta.entity

import com.baomidou.mybatisplus.annotation.*
import java.time.LocalDateTime

@TableName("permission")
class PermissionDO(
    @TableId(type = IdType.AUTO)
    var id: Long?,
    var code: String,
    var name: String,
    var status: Int = 0,
    var createTime: LocalDateTime = LocalDateTime.now(),
    var updateTime: LocalDateTime = LocalDateTime.now(),
)