package com.github.blanexie.vxpt.bbs.user.meta.entity

import com.baomidou.mybatisplus.annotation.*
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler
import java.time.LocalDateTime

@TableName("role")
class RoleDO(
    @TableId(type = IdType.AUTO)
    var id: Long?,
    var code: String,
    var name: String,
    @TableField(typeHandler = FastjsonTypeHandler::class)
    var permissions: List<String>,
    @TableField(typeHandler = FastjsonTypeHandler::class)
    var roles: List<String>,
    var status: Int = 0,
    var createTime: LocalDateTime = LocalDateTime.now(),
    var updateTime: LocalDateTime = LocalDateTime.now(),
)