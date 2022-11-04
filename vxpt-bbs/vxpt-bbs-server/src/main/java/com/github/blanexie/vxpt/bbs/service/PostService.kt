package com.github.blanexie.vxpt.bbs.service

import com.github.blanexie.vxpt.bbs.api.dto.PostDTO
import com.github.blanexie.vxpt.bbs.jpa.entity.PostDO
import com.github.blanexie.vxpt.bbs.jpa.repository.PostRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class PostService(
    val psotRepository: PostRepository
) {

    fun savePost(postDTO: PostDTO) {
        val postDO = PostDO(
            null,
            postDTO.title,
            postDTO.cover,
            postDTO.category,
            postDTO.labels,
            postDTO.content,
            postDTO.userId,
            0,
            LocalDateTime.now(),
            LocalDateTime.now()
        )
        psotRepository.save(postDO)
    }


}