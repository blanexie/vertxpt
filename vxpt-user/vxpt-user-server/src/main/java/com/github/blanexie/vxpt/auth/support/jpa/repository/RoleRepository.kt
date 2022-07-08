package com.github.blanexie.vxpt.auth.support.jpa.repository

import com.github.blanexie.vxpt.auth.support.jpa.entity.RoleDO
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : CrudRepository<RoleDO, Int> {


   fun findByCode(code:String):RoleDO?
}