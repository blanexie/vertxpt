package com.github.blanexie.vxpt.bbs.service

import cn.hutool.crypto.digest.DigestUtil
import com.github.blanexie.vxpt.bbs.entity.ResDO
import com.github.blanexie.vxpt.bbs.entity.ResUserDO
import com.github.blanexie.vxpt.bbs.repository.ResourceRepository
import com.github.blanexie.vxpt.bbs.repository.ResourceUserRepository
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 *
 * @author ：xiezc
 * @date   ：2022/11/9 7:25 PM
 */
@Service
class ResourceService(val resourceRepository: ResourceRepository, val resourceUserRepository: ResourceUserRepository) {

    fun upload(userId: Int, content: ByteArray, fileName: String) {
        var sha1Hex = DigestUtil.sha1Hex(content)
        var suffix = fileName.substringAfterLast(".")
        val resDO = ResDO(null, sha1Hex, suffix, content, 0, LocalDateTime.now(), LocalDateTime.now())
        resourceRepository.save(resDO)
        var resUserDO = ResUserDO(null, userId, sha1Hex, fileName, 0, LocalDateTime.now(), LocalDateTime.now())
        resourceUserRepository.save(resUserDO)
    }

    fun find(userId: Int, sha1Hex: String): ResDO {
        val resUserDO = ResUserDO()
        resUserDO.hash = sha1Hex
        resUserDO.userId = userId
        val findOne = resourceUserRepository.findOne(Example.of(resUserDO))
        if (findOne.isPresent) {
            throw Error("资源不存在")
        } else {
            return resourceRepository.findByHash(sha1Hex)
        }
    }

}