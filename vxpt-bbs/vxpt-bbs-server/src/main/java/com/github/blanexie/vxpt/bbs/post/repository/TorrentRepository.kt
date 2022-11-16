package com.github.blanexie.vxpt.bbs.post.repository

import com.github.blanexie.vxpt.bbs.post.entity.TorrentDO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TorrentRepository : JpaRepository<TorrentDO, Int> {

    fun findByInfoHash(infoHash: String): TorrentDO?

}