package com.github.blanexie.vxpt.bbs.service

import cn.hutool.core.bean.BeanUtil
import cn.hutool.crypto.digest.DigestUtil
import com.dampcake.bencode.Type
import com.github.blanexie.vxpt.bbs.api.dto.TorrentDTO
import com.github.blanexie.vxpt.bbs.entity.TorrentDO
import com.github.blanexie.vxpt.bbs.repository.TorrentRepository
import com.github.blanexie.vxpt.bbs.util.bencode
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TorrentService(
    val torrentRepository: TorrentRepository
) {

    fun buildTorrent(postId: Int, title: String, infoByte: ByteArray): TorrentDO {
        val dictionary = bencode.decode(infoByte, Type.DICTIONARY)
        val infoHash = DigestUtil.sha1Hex(infoByte)
        val size = dictionary["length"] ?: {
            val files = dictionary["files"] as List<Map<String, *>>
            files.sumOf {
                it["length"] as Long
            }
        }
        val infoName = dictionary["name"] as String
        val files = dictionary["files"] as List<Map<String, Any>>
        val pieceLength = dictionary["piece length"] as Long
        val torrentDO = TorrentDO(
            null, postId, title, size as Long, infoHash, infoName, pieceLength,
            files, 0, LocalDateTime.now(), LocalDateTime.now()
        )
        return torrentRepository.save(torrentDO)
    }

    fun findByInfoHash(infoHash: String): TorrentDTO {
        val torrentDO = torrentRepository.findByInfoHash(infoHash) ?: throw Error("请传入正确的infoHash")
        return BeanUtil.copyProperties(torrentDO, TorrentDTO::class.java)
    }

}