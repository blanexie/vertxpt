package com.github.blanexie.vxpt.bbs.service

import com.github.blanexie.vxpt.bbs.repository.ResourceRepository
import com.github.blanexie.vxpt.bbs.repository.ResourceUserRepository
import org.springframework.stereotype.Service

/**
 *
 * @author ：xiezc
 * @date   ：2022/11/9 7:25 PM
 */
@Service
class ResourceService(val resourceRepository: ResourceRepository, val resourceUserRepository: ResourceUserRepository) {

    fun upload(userId: Int, content: ByteArray, fileName: String) {

    }

}