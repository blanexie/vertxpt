package com.github.blanexie.vxpt.bbs.domain.entity

import cn.hutool.core.bean.BeanUtil
import com.github.blanexie.vxpt.bbs.api.dto.PostDTO
import com.github.blanexie.vxpt.bbs.support.jpa.entity.TorrentDO
import com.github.blanexie.vxpt.bbs.support.jpa.repository.TorrentRepository


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