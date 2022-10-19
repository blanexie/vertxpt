package com.github.blanexie.vxpt.user.service

import com.github.blanexie.vxpt.user.entity.RoleDO
import com.github.blanexie.vxpt.user.repository.RoleRepository
import org.springframework.stereotype.Component

@Component
class RoleService(
    private val roleRepository: RoleRepository
) {

    fun findRole(roleCode: String): RoleDO? {
        return roleRepository.findByCode(roleCode)
    }

}