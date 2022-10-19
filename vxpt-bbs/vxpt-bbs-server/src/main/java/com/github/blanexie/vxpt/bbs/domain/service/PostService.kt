package com.github.blanexie.vxpt.bbs.domain.service

import com.github.blanexie.vxpt.bbs.domain.entity.Label
import com.github.blanexie.vxpt.bbs.domain.factory.PostFactory
import com.github.blanexie.vxpt.bbs.domain.factory.TorrentFactory

class PostService(
    val postFactory: PostFactory,
    val torrentFactory: TorrentFactory
) {

    fun savePost(
        title: String,
        cover: String,
        category: String,
        labels: Set<Label>,
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
        val torrentEntitys = torrentFactory.findByPostId(postId)
        if (torrentEntitys.isEmpty()) {
            throw Error("postId 还没有torrent， 无法发布");
        }
        val postEntity = postFactory.findByPostId(postId)
        postEntity.status = 1
        postFactory.save(postEntity)
    }


}