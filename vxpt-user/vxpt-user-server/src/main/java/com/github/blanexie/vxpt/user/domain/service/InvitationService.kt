package com.github.blanexie.vxpt.user.domain.service

import com.github.blanexie.vxpt.user.domain.factory.AccountEntityFactory
import com.github.blanexie.vxpt.user.domain.factory.InvitationEntityFactory

class InvitationService(
    private val invitationEntityFactory: InvitationEntityFactory,
    private val accountEntityFactory: AccountEntityFactory
) {

    /**
     * 创建一个邀请函
     */
    fun createInvitation(userId: Int, email: String) {
        val accountEntity = accountEntityFactory.findByUserId(userId)
        //邀请数量减一
        accountEntity.addInvitationCount(-1)
        //创建邀请函
        val invitationEntity = invitationEntityFactory.createInvitation(userId, email)
        accountEntityFactory.save(accountEntity)
        invitationEntityFactory.save(invitationEntity)
    }


}