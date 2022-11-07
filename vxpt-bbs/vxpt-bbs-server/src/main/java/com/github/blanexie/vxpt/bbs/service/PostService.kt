package com.github.blanexie.vxpt.bbs.service


import cn.hutool.core.bean.BeanUtil
import com.github.blanexie.vxpt.bbs.api.dto.PostDTO
import com.github.blanexie.vxpt.bbs.entity.PostDO
import com.github.blanexie.vxpt.bbs.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class PostService(
    val postRepository: PostRepository
) {

    fun findById(id: Int): PostDTO? {
        val postDO = postRepository.findById(id)
        if (postDO.isPresent) {
            return BeanUtil.copyProperties(postDO.get(), PostDTO::class.java)
        }
        return null
    }

    fun publish(id: Int, userId: Int) {
        val postDO = postRepository.findByIdOrNull(id)
        if (postDO != null && postDO.userId == userId) {
            postDO.publish()
            postRepository.save(postDO)
        }
        throw Error("请传入正确的帖子id")
    }

    fun savePost(postDTO: PostDTO): Int {
        val postDO = PostDO(
            postDTO.id,
            postDTO.title,
            postDTO.cover,
            postDTO.category,
            postDTO.content,
            postDTO.userId,
            0,
            LocalDateTime.now(),
            LocalDateTime.now()
        )
        val save = postRepository.save(postDO)
        return save.id
    }


}