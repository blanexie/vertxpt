package com.github.blanexie.vxpt.bbs.jpa.repository

import com.github.blanexie.vxpt.bbs.jpa.entity.PostDO
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : CrudRepository<PostDO, Int> {
}