package com.github.blanexie.vxpt.bbs.domain.entity

import cn.hutool.core.bean.BeanUtil
import com.github.blanexie.vxpt.bbs.api.dto.PostDTO
import com.github.blanexie.vxpt.bbs.support.jpa.entity.PostDO
import com.github.blanexie.vxpt.bbs.support.jpa.repository.PostRepository
import java.time.LocalDateTime
import javax.persistence.*


/**
 * 帖子
 */
class PostEntity(
    var id: Int,
    var title: String,
    var cover: String,
    var category: String,
    var labels: Set<String>,
    var content: String,
    var userId: Int,
    val createTime: LocalDateTime,
    var status: Int = 0,
) {

    fun putStatus(status: Int) {
        this.status = status
    }

    fun putUserId(userId: Int) {
        this.userId = userId
    }

    fun putCategory(category: String) {
        this.category = category
    }

    fun putLabels(labels: Set<String>) {
        this.labels = labels
    }

    fun putContent(labels: Set<String>) {
        this.labels = labels
    }

    fun putCover(cover: String) {
        this.cover = cover
    }

    fun putTitle(title: String) {
        this.title = title
    }


}




