package com.github.blanexie.vxpt.user.support

import com.github.blanexie.vxpt.user.domain.entity.AccountEntity
import com.github.blanexie.vxpt.user.domain.factory.AccountEntityFactory
import com.github.blanexie.vxpt.user.support.jpa.entity.AccountDO
import com.github.blanexie.vxpt.user.support.jpa.repository.AccountRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import javax.annotation.Resource

@Component
class AccountEntityFactoryImpl : AccountEntityFactory {

    @Resource
    lateinit var accountRepository: AccountRepository

    override fun findByUserId(userId: Int): AccountEntity {
        val accountDO = accountRepository.findFirstByUserId(userId)
        accountDO ?: throw Error("userId: ${userId}  未找到对应的账户  ")
        return AccountEntity(
            accountDO.id,
            accountDO.userId,
            accountDO.level,
            accountDO.points,
            accountDO.invitationCount,
            accountDO.completeCount,
            accountDO.activeCount,
            accountDO.download,
            accountDO.upload,
            accountDO.createTime
        )
    }

    override fun save(accountEntity: AccountEntity) {
        val accountDO = AccountDO()
        accountDO.id = accountEntity.id
        accountDO.userId = accountEntity.userId
        accountDO.level = accountEntity.level
        accountDO.points = accountEntity.points
        accountDO.invitationCount = accountEntity.invitationCount
        accountDO.completeCount = accountEntity.completeCount
        accountDO.activeCount = accountEntity.activeCount
        accountDO.download = accountEntity.download
        accountDO.upload = accountEntity.upload
        accountDO.createTime = accountEntity.createTime
        accountDO.updateTime = LocalDateTime.now()
        accountRepository.save(accountDO)
    }

    override fun nextSeqId(): Int {
        return accountRepository.nextSeqId()
    }

}