package com.github.blanexie.vxpt.bbs.controller

import cn.dev33.satoken.stp.StpUtil
import cn.dev33.satoken.util.SaResult
import com.github.blanexie.vxpt.api.bbs.dto.PostDTO
import com.github.blanexie.vxpt.bbs.service.PostService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/post")
class PostController(val postService: PostService) {

    @PostMapping("/upload")
    fun uploadPost(@RequestBody postDTO: PostDTO): SaResult {
        postDTO.userId = StpUtil.getLoginIdAsInt()
        var postId = postService.savePost(postDTO)
        return SaResult.data(postId)
    }

    @PostMapping("/publish")
    fun publishPost(@RequestParam postId: Int): SaResult {
        val userId = StpUtil.getLoginIdAsInt()
        postService.publish(postId, userId)
        return SaResult.ok()
    }

    @GetMapping("/get")
    fun getPost(@RequestParam postId: Int): SaResult {
        var postDTO = postService.findById(postId)
        return SaResult.data(postDTO)
    }

    fun searchPost(): SaResult {
        TODO("Not yet implemented")
    }
}