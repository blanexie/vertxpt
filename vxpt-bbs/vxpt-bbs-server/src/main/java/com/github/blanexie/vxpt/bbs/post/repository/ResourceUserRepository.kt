package com.github.blanexie.vxpt.bbs.post.repository

import com.github.blanexie.vxpt.bbs.post.entity.ResUserDO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface ResourceUserRepository : JpaRepository<ResUserDO, Int>, JpaSpecificationExecutor<ResUserDO> {

}