package com.github.blanexie.vxpt.auth.domain.entity

import kotlin.streams.toList

/**
 *
 * @author ：xiezc
 * @date   ：2022/7/11 7:19 PM
 */
class PathEntity(
    val path: String,
    val permissions: MutableSet<String>,  // 权限code列表
    val roles: MutableSet<String>
) {


    fun addPath(permissions: Set<PermissionValue>) {
        val pes = permissions.parallelStream().map(PermissionValue::code).toList()
        this.permissions.addAll(pes)
    }

    fun addRole(role: String) {
        this.roles.add(role)
    }


}