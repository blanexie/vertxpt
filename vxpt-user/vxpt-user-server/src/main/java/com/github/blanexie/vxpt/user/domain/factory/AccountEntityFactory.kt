package com.github.blanexie.vxpt.user.domain.factory

import com.github.blanexie.vxpt.user.domain.entity.AccountEntity

/**
 *
 * @author ：xiezc
 * @date   ：2022/7/6 7:47 PM
 */
interface AccountEntityFactory {

    fun findByUserId(userId:Int) :AccountEntity

    fun save(accountEntity: AccountEntity)
    /**
     * 获取序列值作为主键
     */
    fun nextSeqId(): Int

}