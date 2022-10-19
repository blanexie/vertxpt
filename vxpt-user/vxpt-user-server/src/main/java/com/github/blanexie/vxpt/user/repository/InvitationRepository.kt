package com.github.blanexie.vxpt.user.repository

import com.github.blanexie.vxpt.user.entity.InvitationDO
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface InvitationRepository : CrudRepository<InvitationDO, Int> {

    fun findByCodeAndStatus(code: String, status:Int): InvitationDO?

    fun findByUserIdAndStatus(userId: Int, status:Int):List<InvitationDO>

    @Query("select nextval('invitation_id_seq')", nativeQuery = true)
    fun nextSeqId():Int
}