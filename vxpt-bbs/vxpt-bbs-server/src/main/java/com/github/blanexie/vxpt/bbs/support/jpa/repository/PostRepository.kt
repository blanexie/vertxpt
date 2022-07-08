package com.github.blanexie.vxpt.bbs.support.jpa.repository

import com.github.blanexie.vxpt.bbs.support.jpa.entity.PostDO
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : CrudRepository<PostDO, Int> {
}