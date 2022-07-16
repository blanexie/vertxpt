package com.github.blanexie.vxpt.bbs.domain.service

import com.github.blanexie.vxpt.bbs.domain.entity.TorrentEntity
import com.github.blanexie.vxpt.bbs.domain.factory.TorrentFactory

class TorrentService(
    val torrentFactory: TorrentFactory
) {

    fun buildTorrent(postId: Int, title: String, infoByte: ByteArray) {
        val torrentEntity = torrentFactory.create(postId, title, infoByte)
        torrentFactory.save(torrentEntity)
    }


}