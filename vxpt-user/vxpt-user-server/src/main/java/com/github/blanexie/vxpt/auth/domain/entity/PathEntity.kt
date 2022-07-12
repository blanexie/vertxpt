package com.github.blanexie.vxpt.auth.domain.entity

import cn.hutool.core.collection.CollUtil
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

    /**
     * 校验权限
     */
    fun checkPath(permissionCode: Set<String>): Boolean {
        val intersection = CollUtil.intersection(this.permissions, permissionCode)
        return intersection.isEmpty()
    }

}