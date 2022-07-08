package com.github.blanexie.vxpt.bbs.domain.entity

import cn.hutool.core.bean.BeanUtil
import com.github.blanexie.vxpt.bbs.api.dto.PostDTO
import com.github.blanexie.vxpt.bbs.support.jpa.entity.PostDO
import com.github.blanexie.vxpt.bbs.support.jpa.repository.PostRepository


/**
 * 帖子
 */
class PostDomain(val postDTO: PostDTO) {


    fun save(postDTO: PostDTO, postRepository: PostRepository): PostDO {
        val postDO = BeanUtil.copyProperties(postDTO, PostDO::class.java)
        return postRepository.save(postDO)
    }




}




