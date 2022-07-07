package com.github.blanexie.vxpt.auth.server.domain.factory

import com.github.blanexie.vxpt.auth.server.domain.entity.RoleEntity

interface RoleFactory {

    fun findByCode(code: String): RoleEntity?



}