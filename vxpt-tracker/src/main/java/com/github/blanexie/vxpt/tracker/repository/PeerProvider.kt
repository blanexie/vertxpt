package com.github.blanexie.vxpt.tracker.repository

import com.alibaba.fastjson2.JSON
import com.github.blanexie.vxpt.ioc.annotation.Bean
import com.github.blanexie.vxpt.tracker.repository.entity.PeerDO
import com.github.blanexie.vxpt.tracker.repository.mapper.PeerMapper
import com.github.blanexie.vxpt.tracker.service.dto.PeerEntity
import org.apache.ibatis.session.SqlSession

@Bean
class PeerProvider {

    private val fastThreadLocal = ThreadLocal<SqlSession>()

    fun findByInfoHash(infoHash: String): List<PeerDO> {
        val peerMapper = getPeerMapper()
        val peerDOs = peerMapper.selectPeers(infoHash)
        return peerDOs
    }


    fun saveOrUpdate(peerEntity: PeerEntity) {
        val peerDO = PeerDO()
        peerDO.id = peerEntity.id
        peerDO.authKey = peerEntity.authKey
        peerDO.infoHash = peerEntity.infoHash
        peerDO.peerId = peerEntity.peerId
        peerDO.trackerId = peerEntity.trackerId
        peerDO.userId = peerEntity.userId
        peerDO.ip = peerEntity.ipAddr.ip
        peerDO.ipv6 = JSON.toJSONString(peerEntity.ipAddr.ipv6)
        peerDO.port = peerEntity.ipAddr.port
        peerDO.compact = peerEntity.ipAddr.compact
        peerDO.downloaded = peerEntity.downloaded.byte()
        peerDO.left = peerEntity.left.bytes
        peerDO.uploaded = peerEntity.uploaded.bytes
        peerDO.event = peerEntity.event
        peerDO.numwant = peerEntity.numwant
        peerDO.status = peerEntity.status

        val peerMapper = getPeerMapper()
        val peerDOExist = peerMapper.selectByAuthKeyAndInfoHash(peerEntity.authKey, peerEntity.infoHash)
        if (peerDOExist == null) {
            peerMapper.save(peerDO)
        } else {
            peerDO.id = peerDOExist.id
            peerMapper.update(peerDO)
        }
        peerEntity.id = peerDO.id
    }

    private fun getPeerMapper(): PeerMapper {
        if (fastThreadLocal.get() == null) {
            fastThreadLocal.set(buildSqlSessionFactory().openSession(true))
        }
        val sqlSession = fastThreadLocal.get()
        val peerMapper = sqlSession.getMapper(PeerMapper::class.java)
        return peerMapper
    }


}

