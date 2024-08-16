package com.github.blanexie.vxpt.crawler.text.entity

import com.baomidou.mybatisplus.annotation.*
import java.util.*


/**
 *
 * @author xiezc
 * @date 2024/8/16 9:59
 */
@TableName("book")
data class Book(
        @TableId(type = IdType.AUTO)
        var id: Long?,
        var url: String,
        var title: String,
        var author: String,
        var type: String?,
        var coverImg: String?,
        var description: String?,
        var lastUpdateTime: Date?,
        @TableField(fill = FieldFill.INSERT)
        var createTime: Date,
        @TableField(fill = FieldFill.INSERT_UPDATE)
        var updateTime: Date,
)
