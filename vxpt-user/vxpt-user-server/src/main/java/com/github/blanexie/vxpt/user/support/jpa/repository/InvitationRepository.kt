package com.github.blanexie.vxpt.user.support.jpa.repository

import com.github.blanexie.vxpt.user.support.jpa.entity.InvitationDO
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface InvitationRepository : CrudRepository<InvitationDO, Int> {

    fun findTopByCode(code: String):InvitationDO?

    @Query("select nextval('invitation_id_seq')", nativeQuery = true)
    fun nextSeqId():Int
}