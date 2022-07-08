package com.github.blanexie.vxpt.user.support

import cn.hutool.core.util.IdUtil
import cn.hutool.core.util.RandomUtil
import com.github.blanexie.vxpt.user.domain.entity.InvitationEntity
import com.github.blanexie.vxpt.user.domain.factory.AccountEntityFactory
import com.github.blanexie.vxpt.user.domain.factory.InvitationEntityFactory
import com.github.blanexie.vxpt.user.support.jpa.entity.InvitationDO
import com.github.blanexie.vxpt.user.support.jpa.repository.InvitationRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import javax.annotation.Resource

@Component
class InvitationEntityFactoryImpl : InvitationEntityFactory {

    @Resource
    lateinit var invitationRepository: InvitationRepository

    override fun findByCode(code: String): InvitationEntity {
        val invitationDO = invitationRepository.findTopByCode(code)
        invitationDO ?: throw Error("code: ${code}  未找到对应邀请函  ")
        return InvitationEntity(
            invitationDO.id,
            invitationDO.code,
            invitationDO.email,
            invitationDO.userId,
            invitationDO.receiveId,
            invitationDO.expireTime,
            invitationDO.createTime
        )
    }

    override fun save(invitationEntity: InvitationEntity) {
        val invitationDO = InvitationDO()
        invitationDO.id = invitationEntity.id
        invitationDO.code = invitationEntity.code
        invitationDO.email = invitationEntity.email
        invitationDO.userId = invitationEntity.userId
        invitationDO.receiveId = invitationEntity.receiveId
        invitationDO.expireTime = invitationEntity.expireTime
        invitationDO.createTime = invitationEntity.expireTime
        invitationDO.updateTime = LocalDateTime.now()
        invitationRepository.save(invitationDO)
    }

    override fun nextSeqId(): Int {
        return invitationRepository.nextSeqId()
    }

    override fun createInvitation(userId: Int, email: String): InvitationEntity {
        val invitationDO = InvitationDO()
        invitationDO.code = RandomUtil.randomString(10)
        invitationDO.email = email
        invitationDO.userId = userId
        //invitationDO.receiveId = invitationEntity.receiveId
        invitationDO.expireTime = LocalDateTime.now().plusDays(7)
        invitationDO.createTime = LocalDateTime.now()
        invitationDO.updateTime = LocalDateTime.now()
        invitationRepository.save(invitationDO)

       return InvitationEntity(
            invitationDO.id,
            invitationDO.code,
            invitationDO.email,
            invitationDO.userId,
            invitationDO.receiveId,
            invitationDO.expireTime,
            invitationDO.createTime
        )
    }
}