package com.github.blanexie.vxpt.bbs.domain.factory

import com.github.blanexie.vxpt.bbs.domain.entity.PostEntity

interface PostFactory {

    fun build(
        title: String,
        cover: String,
        category: String,
        labels: Set<String>,
        content: String,
        userId: Int,
    ): PostEntity

    fun save(postEntity: PostEntity)

    fun findByPostId(id: Int): PostEntity


}