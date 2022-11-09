package com.github.blanexie.vxpt.bbs.repository

import com.github.blanexie.vxpt.bbs.entity.LabelDO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LabelRepository : JpaRepository<LabelDO, Int> {

    fun findByLabelAndPostId(label: String, postId: Int): LabelDO?

}