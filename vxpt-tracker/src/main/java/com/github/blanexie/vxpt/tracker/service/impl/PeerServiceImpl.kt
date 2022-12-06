package com.github.blanexie.vxpt.tracker.service.impl

import cn.hutool.core.lang.Singleton
import cn.hutool.core.util.HexUtil
import com.alibaba.fastjson2.JSON
import com.github.blanexie.vxpt.tracker.common.Bean
import com.github.blanexie.vxpt.tracker.common.Inject
import com.github.blanexie.vxpt.tracker.repository.PeerProvider
import com.github.blanexie.vxpt.tracker.repository.buildSqlSessionFactory
import com.github.blanexie.vxpt.tracker.repository.entity.PeerDO
import com.github.blanexie.vxpt.tracker.repository.mapper.PeerMapper
import com.github.blanexie.vxpt.tracker.service.dto.PeerEntity
import com.github.blanexie.vxpt.tracker.user.UserService
import io.netty.buffer.Unpooled
import com.github.blanexie.vxpt.tracker.service.PeerService as PeerService

@Bean
class PeerServiceImpl : PeerService {

    @Inject
    lateinit var userService: UserService;
    //private val userService: UserService = Singleton.get(UserService::class.java)
    @Inject
    lateinit var peerProvider: PeerProvider;
    //= Singleton.get("peerProvider")

    override fun announce(peerEntity: PeerEntity): Map<String, Any> {
        //1. 校验用户是否正常，
        val user = userService.findByAuthKeyAndInfoHash(peerEntity.authKey, peerEntity.infoHash)
        if (user.code != 200) {
            return buildFailResp(user.msg)
        }
        peerEntity.userId = user.id
        //2. 保存上报的数据
        peerProvider.saveOrUpdate(peerEntity)
        //3. 查询响应数据
        val peerDOs = peerProvider.findByInfoHash(peerEntity.infoHash)
        //4. 构建响应
        return buildResp(peerDOs)
    }

    override fun scrape() {
        TODO("Not yet implemented")
    }

    override fun stopUpload(uid: Int?, infoHash: String?) {
        TODO("Not yet implemented")
    }

    override fun stopDownload(uid: Int?, infoHash: String?) {
        TODO("Not yet implemented")
    }

    /**
     * 构建响应
     */
    private fun buildFailResp(msg: String): Map<String, Any> {
        val resp = hashMapOf<String, Any>()
        resp["failure reason"] = msg
        return resp
    }

    /**
     * 构建响应
     */
    private fun buildResp(peerDOs: List<PeerDO>): Map<String, Any> {
        val count = peerDOs.count { it.event == "completed" }
        val resp = hashMapOf<String, Any>()
        resp["interval"] = 3600
        resp["min interval"] = 30
        resp["incomplete"] = peerDOs.size - count
        resp["complete"] = count
        val bytebuf = Unpooled.buffer()
        peerDOs
            .filter { it.ip != null }
            .map { getCompactPeer(it.ip!!, it.port) }
            .forEach { bytes -> bytebuf.writeBytes(bytes) }
        val nioBuffer = bytebuf.nioBuffer()
        if (bytebuf.readableBytes() > 0) {
            resp["peers"] = nioBuffer
        }
        val bytebuf6 = Unpooled.buffer()
        peerDOs
            .filter { it.ipv6 != null }.filterNotNull()
            .map {
                val list = JSON.parseArray(it.ipv6)
                list?.map { str -> getCompactPeer6(str as String, it.port) }
                    ?.reduce { acc, bytes -> acc.plus(bytes) }
            }
            .forEach { bytebuf6.writeBytes(it) }
        if (bytebuf6.readableBytes() > 0) {
            resp["peers6"] = bytebuf6.nioBuffer()
        }
        return resp
    }

    private fun getCompactPeer6(ipv6: String, port: Int): ByteArray {
        val ipv6Bytes = compactIpv6(ipv6)
        val p1 = port ushr 8
        val p2 = port and 255
        return ipv6Bytes.plus(p1.toByte()).plus(p2.toByte())
    }

    private fun getCompactPeer(ip: String, port: Int): ByteArray {
        val map = ip.split(".").map { it.toInt().toByte() }
        val p1 = port ushr 8
        val p2 = port and 255
        val plus = map.plus(p1.toByte()).plus(p2.toByte())
        return plus.toByteArray()
    }

    private fun compactIpv6(ipv6: String): ByteArray {
        if (ipv6.startsWith("::")) {
            val split = ipv6.split("::")
            val s2 = split[1]
            val decodeHex1 = HexUtil.decodeHex(s2)
            val i = 16 - -decodeHex1.size
            return ByteArray(i).plus(decodeHex1)
        }
        if (ipv6.endsWith("::")) {
            val split = ipv6.split("::")
            val s2 = split[0]
            val decodeHex1 = HexUtil.decodeHex(s2)
            val i = 16 - -decodeHex1.size
            return decodeHex1.plus(ByteArray(i))
        }
        if (ipv6.contains("::")) {
            val split = ipv6.split("::")
            val s1 = split[0]
            val s2 = split[1]
            val decodeHex = HexUtil.decodeHex(s1)
            val decodeHex1 = HexUtil.decodeHex(s2)
            val i = 16 - decodeHex.size - decodeHex1.size
            return decodeHex.plus(ByteArray(i)).plus(decodeHex1)
        }
        val split = ipv6.split(":")
        return split.map { HexUtil.decodeHex(it) }.reduce { acc, bytes -> acc.plus(bytes) }
    }
}