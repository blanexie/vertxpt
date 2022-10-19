package com.github.blanexie.vxpt.bbs.support.factory

import cn.hutool.crypto.digest.DigestUtil
import com.dampcake.bencode.Type
import com.github.blanexie.vxpt.bbs.domain.entity.TorrentEntity
import com.github.blanexie.vxpt.bbs.domain.factory.TorrentFactory
import com.github.blanexie.vxpt.bbs.support.util.bencode
import java.time.LocalDateTime

class TorrentFactoryImpl : TorrentFactory {


    override fun create(postId: Int, title: String, infoByte: ByteArray): TorrentEntity {
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
        return TorrentEntity(
            infoHash,
            title,
            postId,
            size as Long,
            infoName,
            pieceLength,
            files,
            infoByte,
            LocalDateTime.now()
        )
    }

    override fun findByInfoHash(infoHash: String): TorrentEntity {
        TODO("Not yet implemented")
    }

    override fun save(torrentEntity: TorrentEntity) {
        TODO("Not yet implemented")
    }

    override fun findByPostId(postId: Int): List<TorrentEntity> {
        TODO("Not yet implemented")
    }


}