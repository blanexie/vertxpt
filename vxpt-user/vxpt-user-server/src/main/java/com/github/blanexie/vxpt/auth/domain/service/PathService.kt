package com.github.blanexie.vxpt.auth.domain.service

import com.github.blanexie.vxpt.auth.domain.factory.PathFactory
import com.github.blanexie.vxpt.auth.domain.factory.RoleFactory

/**
 *
 * @author ：xiezc
 * @date   ：2022/7/12 5:14 PM
 */
class PathService(
    private val pathFactory: PathFactory
) {


    fun checkPath(path: String, permissionCode: Set<String>): Boolean {
        val pathEntity = pathFactory.findByPath(path)
        return pathEntity?.checkPath(permissionCode) ?: false
    }

}