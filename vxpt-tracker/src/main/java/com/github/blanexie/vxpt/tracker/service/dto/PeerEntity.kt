package com.github.blanexie.vxpt.tracker.service.dto

import java.time.LocalDateTime

/**
 *
 * @author ：xiezc
 * @date   ：2022/12/1 11:44 AM
 */
data class PeerEntity(
    var id: Int?,
    val infoHash: String,
    val peerId: String,
    var trackerId: String?,
    val authKey: String,
    var userId: Int?,
    var ipAddress: IpAddress,

    var downloaded: Size,
    var left: Size,
    var uploaded: Size,

    var event: String,
    var numwant: Int,

    var status: Int,
    val createTime: LocalDateTime,
    var updateTime: LocalDateTime,
) {
    init {
        // 1.1 校验ip正确与否
        if (ipAddress.ip == null && (ipAddress.ipv6 == null || ipAddress.ipv6?.isEmpty() == true)) {
            throw Error("上报时必须传入ip值")
        }
    }
}
