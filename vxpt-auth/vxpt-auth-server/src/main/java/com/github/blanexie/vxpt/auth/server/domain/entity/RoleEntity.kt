package com.github.blanexie.vxpt.auth.server.domain.entity

class RoleEntity(
    val code: String,
    val name: String,
    val permissions: MutableList<PermissionValue>,
    val roles: MutableList<String>,
) {

    fun addPermission(code: PermissionValue) {
        this.permissions.add(code)
    }

    fun removePermission(code: PermissionValue) {
        this.permissions.remove(code)
    }

    fun addRole(code: String) {
        this.roles.add(code)
    }

    fun removeRole(code: String) {
        this.roles.remove(code)
    }


}