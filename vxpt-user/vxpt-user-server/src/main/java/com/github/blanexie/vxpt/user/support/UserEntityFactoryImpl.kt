package com.github.blanexie.vxpt.user.support

import com.github.blanexie.vxpt.user.domain.entity.UserEntity
import com.github.blanexie.vxpt.user.domain.factory.UserEntityFactory
import com.github.blanexie.vxpt.user.support.jpa.entity.UserDO
import com.github.blanexie.vxpt.user.support.jpa.repository.UserRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import javax.annotation.Resource

@Component
class UserEntityFactoryImpl : UserEntityFactory {

    @Resource
    lateinit var userRepository: UserRepository

    override fun findByUserId(userId: Int): UserEntity {
        val userDO = userRepository.findById(userId)
        val userEntity = userDO.map {
            UserEntity(it.id, it.nickName, it.email, it.pwd, it.sex, it.role, it.createTime)
        }.orElseThrow()
        return userEntity
    }

    override fun findByEmail(email: String): UserEntity {
        val userDO = userRepository.findFirstByEmail(email)
        userDO ?: throw Error("userId: ${email}  未找到对应的账户  ")
        return UserEntity(
            userDO.id, userDO.nickName, userDO.email, userDO.pwd, userDO.sex, userDO.role, userDO.createTime
        )
    }

    override fun findByNickName(nickName: String): UserEntity {
        val userDO = userRepository.findFirstByNickName(nickName)
        userDO ?: throw Error("userId: ${nickName}  未找到对应的账户  ")
        return UserEntity(
            userDO.id, userDO.nickName, userDO.email, userDO.pwd, userDO.sex, userDO.role, userDO.createTime
        )
    }

    override fun nextSeqId(): Int {
        return userRepository.nextSeqId()
    }

    override fun save(userEntity: UserEntity) {
        val userDO = UserDO()
        userDO.id = userEntity.id
        userDO.nickName = userEntity.nickName
        userDO.email = userEntity.email
        userDO.pwd = userEntity.pwd
        userDO.sex = userEntity.sex
        userDO.role = userEntity.role
        userDO.createTime = userEntity.createTime
        userDO.updateTime = LocalDateTime.now()
        userRepository.save(userDO)
    }
}