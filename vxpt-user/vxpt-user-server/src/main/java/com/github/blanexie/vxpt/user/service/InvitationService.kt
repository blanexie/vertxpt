package com.github.blanexie.vxpt.user.service

import cn.hutool.core.util.RandomUtil
import com.github.blanexie.vxpt.user.entity.InvitationDO
import com.github.blanexie.vxpt.user.repository.InvitationRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class InvitationService(
    private val invitationRepository: InvitationRepository
) {


    fun findByCode(code: String): InvitationDO? {
        return invitationRepository.findByCodeAndStatus(code, 0)
    }

    fun use(code: String, receiveUserId: Int): Int {
        var invitationDO = invitationRepository.findByCodeAndStatus(code, 0)
        invitationDO!!.use(receiveUserId)
        invitationRepository.save(invitationDO)
        return invitationDO.id
    }

    /**
     * 创建一个邀请函
     */
    fun createInvitation(senderUserId: Int, receiverEmail: String): InvitationDO {
        val invitationDOs = invitationRepository.findBySenderUserIdAndStatus(senderUserId, 0)
        if (invitationDOs.isNotEmpty()) {
            throw Error("用户存在待接受的邀请函，无法再次邀请")
        }
        for (i in 1..10) {
            val code = RandomUtil.randomString(8);
            val invitationDO = invitationRepository.findByCodeAndStatus(code, 0);
            if (invitationDO == null) {
                val invitationDO = InvitationDO(
                    null, code, senderUserId, receiverEmail,
                    null, LocalDateTime.now().plusDays(7), 0,
                    LocalDateTime.now(), LocalDateTime.now()
                )
                return invitationRepository.save(invitationDO)
            }
        }
        throw Error("邀请码用完，请稍后再试")
    }


}