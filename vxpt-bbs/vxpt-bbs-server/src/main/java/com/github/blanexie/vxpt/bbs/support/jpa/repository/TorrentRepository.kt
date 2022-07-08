package com.github.blanexie.vxpt.bbs.support.jpa.repository

import com.github.blanexie.vxpt.bbs.support.jpa.entity.TorrentDO
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TorrentRepository : CrudRepository<TorrentDO, Int> {



}