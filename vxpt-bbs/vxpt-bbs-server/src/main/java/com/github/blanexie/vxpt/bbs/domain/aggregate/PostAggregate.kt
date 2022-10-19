package com.github.blanexie.vxpt.bbs.domain.aggregate

import com.github.blanexie.vxpt.bbs.domain.entity.PostEntity
import com.github.blanexie.vxpt.bbs.domain.entity.TorrentEntity


/**
 * 帖子聚合
 */
class PostAggregate(
    val postId: Int,      //帖子id
    val userId: Int,      //发帖者id
    val torrentId: List<String>,  //种子id

    val title: String,    //标题
    val cover: String,     //封面
    val comment: String,    //帖子文本体
    val category: String,   //分类
    var labels: Set<Label>,  //标签
) {


}

data class Label(val type: String, val name: String)

