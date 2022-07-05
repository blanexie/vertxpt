package com.github.blanexie.vxpt.bbs.server.domain

import cn.hutool.core.bean.BeanUtil
import com.github.blanexie.vxpt.bbs.server.domain.dto.PostDTO
import com.github.blanexie.vxpt.bbs.server.domain.entity.TorrentDO
import com.github.blanexie.vxpt.bbs.server.domain.repository.TorrentRepository


/**
 * 种子
 */
class TorrentDomain(val torrentDO: TorrentDO) {

    val id: Int = torrentDO.id!!

    fun save(postDTO: PostDTO, torrentRepository: TorrentRepository): TorrentDO {
        val torrentDO = BeanUtil.copyProperties(postDTO, TorrentDO::class.java)
        return torrentRepository.save(torrentDO)
    }

}