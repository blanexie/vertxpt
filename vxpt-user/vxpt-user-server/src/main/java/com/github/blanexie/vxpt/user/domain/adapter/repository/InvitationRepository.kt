package com.github.blanexie.vxpt.user.domain.adapter.repository

import com.github.blanexie.vxpt.user.domain.entity.InvitationDO
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface InvitationRepository : CrudRepository<InvitationDO, Int> {
}