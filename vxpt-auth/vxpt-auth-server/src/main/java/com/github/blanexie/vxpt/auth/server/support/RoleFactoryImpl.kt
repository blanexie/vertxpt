package com.github.blanexie.vxpt.auth.server.support

import com.github.blanexie.vxpt.auth.server.domain.entity.PermissionValue
import com.github.blanexie.vxpt.auth.server.domain.entity.RoleEntity
import com.github.blanexie.vxpt.auth.server.domain.factory.RoleFactory
import com.github.blanexie.vxpt.auth.server.support.jpa.repository.PermissionRepository
import com.github.blanexie.vxpt.auth.server.support.jpa.repository.RoleRepository
import org.springframework.stereotype.Component
import javax.annotation.Resource


@Component
class RoleFactoryImpl : RoleFactory {

    @Resource
    lateinit var roleRepository: RoleRepository

    @Resource
    lateinit var permissionRepository: PermissionRepository

    override fun findByCode(code: String): RoleEntity? {
        val roleDO = roleRepository.findByCode(code)
        return roleDO?.let {
            val permissions = it.permissions
            val permissionDOs = permissionRepository.findByCodeIn(permissions)
            val permissionValues = permissionDOs.map {
                PermissionValue(it.code, it.name, it.description)
            }.toMutableList()

            RoleEntity(it.code, it.name, permissionValues, it.roles)
        }
    }


}