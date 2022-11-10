package com.github.blanexie.vxpt.bbs.controller

import cn.dev33.satoken.util.SaResult
import com.github.blanexie.vxpt.bbs.service.PostService
import com.github.blanexie.vxpt.bbs.service.TorrentService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/torrent")
class TorrentController(val postService: PostService, val torrentService: TorrentService) {

    @PostMapping("/upload")
    fun uploadTorrent(
        @RequestParam postId: Int,
        @RequestParam title: String,
        @RequestParam infoByte: ByteArray
    ): SaResult {
        val postDTO = postService.findById(postId)
        if (postDTO != null) {
            val buildTorrent = torrentService.buildTorrent(postId, title, infoByte)
            return SaResult.data(buildTorrent.id)
        } else {
            return SaResult.error("请传入正确的帖子id")
        }
    }

    @GetMapping("/find")
    fun findByInfoHash(@RequestParam infoHash: String): SaResult {
        var torrentDTO = torrentService.findByInfoHash(infoHash)
        return SaResult.data(torrentDTO)
    }

}