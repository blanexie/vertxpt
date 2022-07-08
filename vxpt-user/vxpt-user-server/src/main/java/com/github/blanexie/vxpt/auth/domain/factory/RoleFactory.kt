package com.github.blanexie.vxpt.auth.domain.factory

import com.github.blanexie.vxpt.auth.domain.entity.RoleEntity

interface RoleFactory {

    fun findByCode(code: String): RoleEntity?


}