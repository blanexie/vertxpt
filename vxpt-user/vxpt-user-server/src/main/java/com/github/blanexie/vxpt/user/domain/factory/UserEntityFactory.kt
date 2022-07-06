package com.github.blanexie.vxpt.user.domain.factory

import com.github.blanexie.vxpt.user.domain.entity.UserEntity

/**
 *
 * @author ：xiezc
 * @date   ：2022/7/6 6:56 PM
 */
interface UserEntityFactory {

    fun findByUserId(userId: Int): UserEntity

    fun findByEmail(email: String): UserEntity

    /**
     * 获取序列值作为主键
     */
    fun nextSeqId(): Int

    /**
     * 持久化
     */
    fun save(userEntity: UserEntity)

}