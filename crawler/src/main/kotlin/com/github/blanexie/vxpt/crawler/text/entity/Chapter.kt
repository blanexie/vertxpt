package com.github.blanexie.vxpt.crawler.text.entity

import com.baomidou.mybatisplus.annotation.*
import java.util.*


/**
 *
 * @author xiezc
 * @date 2024/8/16 10:00
 */
@TableName("chapter")
data class Chapter(
        @TableId(type = IdType.AUTO)
        var id: Long?,
        var url: String,
        var index: Long?,
        var bookId: Long?,
        var title: String,
        var description: String?,
        var content: String,
        var publishTime: Date?,
        @TableField(fill = FieldFill.INSERT)
        var createTime: Date,
        @TableField(fill = FieldFill.INSERT_UPDATE)
        var updateTime: Date,
        var status: Int = 0,  //初始化没有进行过梳理的类
)