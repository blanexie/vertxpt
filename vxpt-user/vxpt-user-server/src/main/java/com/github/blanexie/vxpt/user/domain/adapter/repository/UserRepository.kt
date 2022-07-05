package com.github.blanexie.vxpt.user.domain.adapter.repository

import com.github.blanexie.vxpt.user.domain.entity.UserDO
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<UserDO, Int> {

    fun findFirstByEmail(email: String): UserDO?

}