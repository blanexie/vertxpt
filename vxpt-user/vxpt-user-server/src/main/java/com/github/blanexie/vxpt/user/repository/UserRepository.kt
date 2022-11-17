package com.github.blanexie.vxpt.user.repository

import com.github.blanexie.vxpt.user.entity.UserDO
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<UserDO, Int> {

    fun findByEmail(email: String): UserDO?

    fun findByNickName(nickName: String): UserDO?

    @Query("select nextval('vxpt_user.user_id_seq')", nativeQuery = true)
    fun nextSeqId(): Int

}