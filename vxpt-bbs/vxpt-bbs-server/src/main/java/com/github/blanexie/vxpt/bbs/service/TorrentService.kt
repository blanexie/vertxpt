package com.github.blanexie.vxpt.bbs.service

import cn.hutool.crypto.digest.DigestUtil
import com.dampcake.bencode.Type
import com.github.blanexie.vxpt.bbs.jpa.entity.TorrentDO
import com.github.blanexie.vxpt.bbs.jpa.repository.TorrentRepository
import com.github.blanexie.vxpt.bbs.util.bencode
import java.time.LocalDateTime

class TorrentService(
    val torrentRepository: TorrentRepository
) {

    fun buildTorrent(postId: Int, title: String, infoByte: ByteArray): TorrentDO {
        val dictionary = bencode.decode(infoByte, Type.DICTIONARY)
        val infoHash = DigestUtil.sha1Hex(infoByte)
        val size = dictionary["length"] ?: {
            val files = dictionary["files"] as List<Map<String, *>>
            files.map {
                it["length"] as Long
            }.sum()
        }
        val infoName = dictionary["name"] as String
        val files = dictionary["files"] as List<Map<String, Any>>
        val pieceLength = dictionary["piece length"] as Long
        val torrentDO = TorrentDO(
            null, postId, title, size as Long,
            infoHash, infoName, pieceLength, files, infoByte,
            0, LocalDateTime.now(), LocalDateTime.now()
        )
        return torrentRepository.save(torrentDO)
    }


}