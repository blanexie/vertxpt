package com.github.blanexie.vxpt.bbs.domain.factory

import com.github.blanexie.vxpt.bbs.domain.entity.TorrentEntity

interface TorrentFactory {

    fun create(postId: Int, title: String, infoByte: ByteArray): TorrentEntity

    fun findByInfoHash(infoHash: String): TorrentEntity

    fun save(torrentEntity: TorrentEntity)

    fun findByPostId(postId: Int, ): List<TorrentEntity>

}