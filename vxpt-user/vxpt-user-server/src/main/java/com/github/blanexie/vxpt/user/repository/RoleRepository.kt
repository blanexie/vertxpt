package com.github.blanexie.vxpt.user.repository

import com.github.blanexie.vxpt.user.entity.RoleDO
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface RoleRepository : CrudRepository<RoleDO, Int> {

    fun findByCode(code: String): RoleDO?



}