package com.github.blanexie.vxpt.user.support.jpa.repository

import com.github.blanexie.vxpt.user.support.jpa.entity.UserDO
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<UserDO, Int> {

    fun findFirstByEmail(email: String): UserDO?


    @Query("select nextval('user_id_seq')", nativeQuery = true)
    fun nextSeqId():Int

}