package com.github.blanexie.vxpt.bbs.post.repository

import com.github.blanexie.vxpt.bbs.post.entity.ResDO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ResourceRepository : JpaRepository<ResDO, Int> {

    fun findByHash(hash: String): ResDO
}