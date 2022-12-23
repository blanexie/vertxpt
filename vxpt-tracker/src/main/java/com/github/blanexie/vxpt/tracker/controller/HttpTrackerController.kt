package com.github.blanexie.vxpt.tracker.controller

import cn.hutool.core.net.URLDecoder
import cn.hutool.core.util.HexUtil
import com.dampcake.bencode.Bencode
import com.github.blanexie.vxpt.ioc.annotation.Component
import com.github.blanexie.vxpt.ioc.annotation.Inject
import com.github.blanexie.vxpt.iocweb.annotation.Mapping
import com.github.blanexie.vxpt.tracker.service.PeerService
import com.github.blanexie.vxpt.tracker.service.dto.IpAddress
import com.github.blanexie.vxpt.tracker.service.dto.PeerEntity
import com.github.blanexie.vxpt.tracker.service.dto.Size
import io.netty.buffer.Unpooled
import io.netty.handler.codec.http.*
import java.net.InetAddress
import java.net.InetSocketAddress
import java.time.LocalDateTime

@Component
class HttpTrackerController {

    private val bencode = Bencode(true)

    @Inject
    lateinit var peerService: PeerService

    @Mapping("/announce")
    fun announce(req: HttpRequest): HttpResponse {
        //获取参数
        val reqMap = getReqParamMap(req)
        val peerEntity = buildPeerEntity(reqMap)
        val resp = peerService.announce(peerEntity)
        val encode = bencode.encode(resp)
        return buildResp(req, encode)
    }

    private fun getReqParamMap(req: HttpRequest): Map<String, List<String>> {
        val uri = req.uri()
        val parms = uri.split("?")[1]
        val kvs = parms.split("&")
        val paramMap = HashMap<String, ArrayList<String>>()
        kvs.forEach {
            val split = it.split("=")
            val value = paramMap.computeIfAbsent(split[0]) {
                ArrayList<String>()
            }
            if (split.size > 1) {
                value.add(split[1])
            }
        }
        //获取ip地址
        getIpAddress(req)?.let {
            paramMap["headerIp"] = arrayListOf(it)
        }
        //处理infoHash
        val infoHash = paramMap["info_hash"]
        val decode = URLDecoder.decode(
            infoHash!!.first().toByteArray(charset("utf8"))
        )
        paramMap["infoHash"] = arrayListOf(HexUtil.encodeHexStr(decode))
        return paramMap
    }
}

fun buildPeerEntity(reqMap: Map<String, List<String>>): PeerEntity {
    val infoHash = reqMap["infoHash"]!!.first()
    val peerId = reqMap["peer_id"]!!.first()
    val port = reqMap["port"]!!.first().toInt()
    val uploaded = reqMap["uploaded"]!!.first().toLong()
    val downloaded = reqMap["downloaded"]!!.first().toLong()
    val left = reqMap["left"]!!.first().toLong()
    // 此参数值为1,表示期望得到紧凑模式的节点列表.
    // 否则表示期望得到普通模式的节点列表.
    // 指出客户端是否支持压缩模式. 如果是,伙伴列表将被一个伙伴字符串所代替.每个伙伴占6个字节.前4个字节是主机(网络字序) , 后2个字节是端口(网络字序).
    val compact = reqMap["compact"]?.first().let { it?.toInt() ?: 1 }

    val event = reqMap["event"]!!.first().let {
        if (left == 0L) "completed" else it ?: "empty"
    }

    //处理peer的上报地址
    val ip = reqMap["ip"].let { it ?: reqMap["headerIp"] }?.first()
        .let {
            if (it != LOCALHOST) it else null
        }
    val ipv6 = reqMap["ipv6"]?.filter { it != LOCALHOSTIPV6 }
        ?.toList()

    //  可选的期望Tracker最大返回数.缺省为50个
    val numwant = reqMap["numwant"].let { it?.first()?.toInt() ?: 50 }
    val authKey = reqMap["auth_key"]!!.first()
    val trackerid = reqMap["trackerid"]?.first()
    val createTime = LocalDateTime.now()
    val updateTime = LocalDateTime.now()

    val ipAddr = IpAddress(ip!!, ipv6, port, compact)

    return PeerEntity(
        null, infoHash, peerId, trackerid, authKey, null, ipAddr, Size(downloaded), Size(left),
        Size(uploaded), event, numwant, 0, createTime, updateTime,
    )
}

fun buildResp(req: HttpRequest, content: ByteArray): HttpResponse {
    return DefaultFullHttpResponse(
        req.protocolVersion(), HttpResponseStatus.OK,
        Unpooled.wrappedBuffer(content)
    )
}


