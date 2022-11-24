package com.github.blanexie.vxpt.user.repository

import com.github.blanexie.vxpt.user.entity.UserDO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<UserDO, Int>, JpaRepository<UserDO, Int> {

    fun findByNickName(nickName: String): UserDO?

    fun findByEmail(email: String): UserDO?

    @Query("select nextval('vxpt_user.user_id_seq')", nativeQuery = true)
    fun nextSeqId(): Int

}