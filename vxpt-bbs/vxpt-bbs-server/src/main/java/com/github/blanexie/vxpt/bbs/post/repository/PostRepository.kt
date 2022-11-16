package com.github.blanexie.vxpt.bbs.post.repository

import com.github.blanexie.vxpt.bbs.post.entity.PostDO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<PostDO, Int>, CrudRepository<PostDO, Int> {

}