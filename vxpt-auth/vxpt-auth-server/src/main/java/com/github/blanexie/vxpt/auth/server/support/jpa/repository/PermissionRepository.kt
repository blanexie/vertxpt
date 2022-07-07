package com.github.blanexie.vxpt.auth.server.support.jpa.repository

import com.github.blanexie.vxpt.auth.server.support.jpa.entity.PermissionDO
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PermissionRepository : CrudRepository<PermissionDO, Int> {

    fun findByCodeIn(codes: List<String>): List<PermissionDO>

}