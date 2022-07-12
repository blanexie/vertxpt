package com.github.blanexie.vxpt.auth.support

import com.github.blanexie.vxpt.auth.domain.entity.PathEntity
import com.github.blanexie.vxpt.auth.domain.factory.PathFactory
import com.github.blanexie.vxpt.auth.support.jpa.repository.PathRepository
import org.springframework.stereotype.Component
import javax.annotation.Resource

/**
 *
 * @author ：xiezc
 * @date   ：2022/7/12 5:20 PM
 */
@Component
class PathFactoryImpl : PathFactory {

    @Resource
    lateinit var pathRepository: PathRepository

    override fun findByPath(path: String): PathEntity? {
        val pathDO = pathRepository.findByPath(path)
        return pathDO?.let {
            PathEntity(pathDO.path, pathDO.permissions, pathDO.roles)
        }
    }
}