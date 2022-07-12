package com.github.blanexie.vxpt.auth.support.jpa.repository

import com.github.blanexie.vxpt.auth.support.jpa.entity.PathDO
import com.github.blanexie.vxpt.auth.support.jpa.entity.PermissionDO
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 *
 * @author ：xiezc
 * @date   ：2022/7/11 7:29 PM
 */
@Repository
interface PathRepository : CrudRepository<PathDO, Int> {

    fun findByPath(path: String): PathDO?

}