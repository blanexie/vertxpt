package com.github.blanexie.vxpt.user.domain.factory

import com.github.blanexie.vxpt.user.domain.entity.AccountEntity
import com.github.blanexie.vxpt.user.domain.entity.InvitationEntity

interface InvitationEntityFactory {

    fun findByCode(code: String): InvitationEntity

    fun save(invitationEntity: InvitationEntity)

    /**
     * 获取序列值作为主键
     */
    fun nextSeqId(): Int

}