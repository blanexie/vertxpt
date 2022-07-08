package com.github.blanexie.vxpt.bbs.domain

import cn.hutool.core.bean.BeanUtil
import com.github.blanexie.vxpt.bbs.domain.dto.PostDTO
import com.github.blanexie.vxpt.bbs.domain.entity.PostDO
import com.github.blanexie.vxpt.bbs.domain.repository.PostRepository


/**
 * 帖子
 */
class PostDomain(val postDTO: PostDTO) {


    fun save(postDTO: PostDTO, postRepository: PostRepository): PostDO {
        val postDO = BeanUtil.copyProperties(postDTO, PostDO::class.java)
        return postRepository.save(postDO)
    }




}




