package com.github.blanexie.vxpt.bbs.domain.service

import com.github.blanexie.vxpt.bbs.domain.factory.PostFactory

class PostService(
    val postFactory: PostFactory
) {


    fun savePost(
        title: String,
        cover: String,
        category: String,
        labels: Set<String>,
        content: String,
        userId: Int,
    ) {
        val postEntity = postFactory.build(title, cover, category, labels, content, userId)
        postFactory.save(postEntity)
    }

    /**
     * 发布， 检查是否有torrent
     */
    fun publish(postId: Int) {

    }


}