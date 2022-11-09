package com.github.blanexie.vxpt.bbs.repository

import com.github.blanexie.vxpt.bbs.entity.ResourceDO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ResourceRepository : JpaRepository<ResourceDO, Int> {

}