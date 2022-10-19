package com.github.blanexie.vxpt.bbs.domain.entity

import cn.hutool.crypto.digest.DigestUtil
import com.dampcake.bencode.Type
import com.github.blanexie.vxpt.bbs.support.util.bencode
import java.time.LocalDateTime


/**
 * 种子
 */
class TorrentEntity(
    val infoHash: String,
    var title: String,
    val size: Long,
    val infoName: String,
    val pieceLength: Long,
    val files: List<InfoFile>?,
    val infoByte: ByteArray,
    val createTime: LocalDateTime,
    var status: Int = 0,
) {

    fun putTitle(title: String) {
        this.title = title
    }

    fun putStatus(status: Int) {
        this.status = status
    }


}


data class InfoFile(
    val length: Long,
    val path: String
)