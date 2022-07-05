package com.github.blanexie.vxpt.user.domain.adapter.repository

import com.github.blanexie.vxpt.user.domain.entity.AccountDO
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface AccountRepository : CrudRepository<AccountDO, Int> {

    fun findFirstByUserId(userId: Int): AccountDO?

}