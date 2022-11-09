package com.github.blanexie.vxpt.bbs.repository

import com.github.blanexie.vxpt.bbs.entity.ResDO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ResourceRepository : JpaRepository<ResDO, Int> {

}