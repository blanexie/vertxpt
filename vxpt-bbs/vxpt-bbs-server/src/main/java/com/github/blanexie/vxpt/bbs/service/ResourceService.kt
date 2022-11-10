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
        var md5Hex = DigestUtil.md5Hex(content)
        var suffix = fileName.substringAfterLast(".")
        val resDO = ResDO(null, md5Hex, suffix, content, 0, LocalDateTime.now(), LocalDateTime.now())
        resourceRepository.save(resDO)
        var resUserDO = ResUserDO(null, userId, md5Hex, fileName, 0, LocalDateTime.now(), LocalDateTime.now())
        resourceUserRepository.save(resUserDO)
    }

    fun download(userId: Int, md5: String): ResDO {
        val resUserDO = ResUserDO()
        resUserDO.hash = md5
        resUserDO.userId = userId
        val findOne = resourceUserRepository.findOne(Example.of(resUserDO))
        if (findOne == null) {
            throw Error("资源不存在")
        } else {
            return resourceRepository.findByHash(md5)
        }
    }

}