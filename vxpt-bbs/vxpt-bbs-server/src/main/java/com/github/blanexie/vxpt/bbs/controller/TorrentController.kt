package com.github.blanexie.vxpt.bbs.controller

import com.github.blanexie.vxpt.bbs.api.TorrentRpc
import com.github.blanexie.vxpt.bbs.api.dto.PostDTO
import com.github.blanexie.vxpt.bbs.api.dto.TorrentDTO
import com.github.blanexie.vxpt.bbs.service.PostService
import com.github.blanexie.vxpt.bbs.service.TorrentService
import org.springframework.web.bind.annotation.RestController

@RestController
class TorrentController(val postService: PostService, val torrentService: TorrentService) : TorrentRpc {

    override fun uploadPost(postDTO: PostDTO): Int {
        return postService.savePost(postDTO);
    }

    override fun uploadTorrent(torrentDTO: TorrentDTO): Int {
        val postDTO = postService.findById(torrentDTO.postId)
        if (postDTO != null) {
            val buildTorrent = torrentService.buildTorrent(torrentDTO.postId, torrentDTO.title, torrentDTO.infoByte)
            return buildTorrent.id
        } else {
            throw Error("请传入正确的帖子id")
        }
    }

    override fun findByInfoHash(infoHash: String): TorrentDTO {
        return torrentService.findByInfoHash(infoHash)
    }

    override fun publishPost(postId: Int, userId: Int) {
        postService.publish(postId, userId)
    }

    override fun getPost(postId: Int): PostDTO? {
        return postService.findById(postId)
    }


    override fun searchPost(): MutableList<PostDTO> {
        TODO("Not yet implemented")
    }
}