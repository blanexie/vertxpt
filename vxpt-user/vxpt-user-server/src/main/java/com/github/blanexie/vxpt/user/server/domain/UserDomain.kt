package com.github.blanexie.vxpt.user.server.domain

import cn.hutool.core.bean.BeanUtil
import cn.hutool.core.date.DateUnit
import cn.hutool.core.date.DateUtil
import cn.hutool.crypto.digest.DigestUtil
import com.github.blanexie.vxpt.user.server.api.dto.UserDTO
import com.github.blanexie.vxpt.user.server.domain.entity.UserDO
import java.util.*

class UserDomain(val userDO: UserDO) {

    val userId: Int = userDO.id!!

    fun checkPwd(pwdSha256Hex: String, timeStamp: Long): Boolean {
        //检查是否超时
        val betweenMinutes = DateUtil.between(Date(), Date(timeStamp), DateUnit.MINUTE)
        if (betweenMinutes > 30) {
            return false
        }
        val secret = "${userDO.email}${userDO.pwd}$timeStamp"
        val sha256Hex = DigestUtil.sha256Hex(secret)
        return pwdSha256Hex == sha256Hex
    }





    fun toDTO(): UserDTO {
       return  BeanUtil.copyProperties(userDO, UserDTO::class.java)
    }


}