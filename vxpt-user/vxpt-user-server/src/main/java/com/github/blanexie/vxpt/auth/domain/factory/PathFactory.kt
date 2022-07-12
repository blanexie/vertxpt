package com.github.blanexie.vxpt.auth.domain.factory

import com.github.blanexie.vxpt.auth.domain.entity.PathEntity

/**
 *
 * @author ：xiezc
 * @date   ：2022/7/11 7:30 PM
 */
interface PathFactory {

    fun findByPath(path: String): PathEntity?

}