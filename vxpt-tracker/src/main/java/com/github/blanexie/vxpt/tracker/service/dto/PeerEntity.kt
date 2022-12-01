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
    var trackerid: String?,
    val authKey: String,

    var ipAddr: IpAddr,

    var downloaded: Size,
    var left: Size,
    var uploaded: Size,

    var event: String,
    var numwant: Int,

    var status: Int,  //
    val createTime: LocalDateTime,
    var updateTime: LocalDateTime,
) {
    init {
        // 1.1 校验ip正确与否
        if (ipAddr.ip == null && (ipAddr.ipv6 == null || ipAddr.ipv6?.isEmpty() == true)) {
            throw Error("上报时必须传入ip值")
        }
    }
}


data class IpAddr(
    var ip: String,
    var ipv6: List<String>?,
    var port: Int,
    var compact: Int,
)

data class Size(val bytes: Long) {

    fun byte(): Long = bytes

    fun KB(): Long = bytes / 1024

    fun MB(): Long = bytes / (1024 * 1024)

    fun GB(): Long = bytes / (1024 * 1024 * 1024)

    fun TB(): Long = bytes / (1024 * 1024 * 1024 * 1024)

    fun PB(): Long = bytes / (1024 * 1024 * 1024 * 1024 * 1024)

}