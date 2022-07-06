package com.github.blanexie.vxpt.user.support.jpa.repository

import com.github.blanexie.vxpt.user.support.jpa.entity.AccountDO
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface AccountRepository : CrudRepository<AccountDO, Int> {

    fun findFirstByUserId(userId: Int): AccountDO?

    @Query("select nextval('account_id_seq')", nativeQuery = true)
    fun nextSeqId(): Int

}