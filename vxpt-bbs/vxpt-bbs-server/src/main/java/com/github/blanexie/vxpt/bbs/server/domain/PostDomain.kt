package com.github.blanexie.vxpt.bbs.server.domain

import cn.hutool.core.bean.BeanUtil
import com.github.blanexie.vxpt.bbs.server.domain.dto.PostDTO
import com.github.blanexie.vxpt.bbs.server.domain.entity.PostDO
import com.github.blanexie.vxpt.bbs.server.domain.repository.PostRepository


/**
 * 帖子
 */
class PostDomain(val postDTO: PostDTO) {


    fun save(postDTO: PostDTO, postRepository: PostRepository): PostDO {
        val postDO = BeanUtil.copyProperties(postDTO, PostDO::class.java)
        return postRepository.save(postDO)
    }




}




