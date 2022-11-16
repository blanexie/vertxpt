package com.github.blanexie.vxpt.bbs.post.repository

import com.github.blanexie.vxpt.bbs.post.entity.LabelDO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LabelRepository : JpaRepository<LabelDO, Int> {

    fun findByLabelAndPostId(label: String, postId: Int): LabelDO?

}