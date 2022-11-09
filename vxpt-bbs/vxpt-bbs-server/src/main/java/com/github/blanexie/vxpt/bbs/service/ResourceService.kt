package com.github.blanexie.vxpt.bbs.service

import cn.hutool.crypto.digest.DigestUtil
import com.github.blanexie.vxpt.bbs.entity.ResDO
import com.github.blanexie.vxpt.bbs.entity.ResUserDO
import com.github.blanexie.vxpt.bbs.repository.ResourceRepository
import com.github.blanexie.vxpt.bbs.repository.ResourceUserRepository
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
        val resUserDO = ResUserDO(null, userId, md5Hex, fileName, 0, LocalDateTime.now(), LocalDateTime.now())
        resourceUserRepository.save(resUserDO)
    }

}