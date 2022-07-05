package com.github.blanexie.vxpt.bbs.server.domain.repository

import com.github.blanexie.vxpt.bbs.server.domain.entity.TorrentDO
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TorrentRepository : CrudRepository<TorrentDO, Int> {



}