package com.github.blanexie.vxpt.user.domain.entity

import cn.hutool.core.bean.BeanUtil
import cn.hutool.core.date.DateUnit
import cn.hutool.core.date.DateUtil
import cn.hutool.crypto.digest.DigestUtil
import com.fasterxml.jackson.annotation.JsonFormat
import com.github.blanexie.vxpt.user.server.api.dto.UserDTO
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

class UserEntity(
    var id: Int,
    val nickName: String,
    val email: String,
    val pwd: String,
    val sex: Int, //1: 男  2: 女  0:未知
    var role: String,  //角色
    val referencesUserId: Int,  //邀请者， 1：admin
    val createTime: LocalDateTime = LocalDateTime.now(),
    val updateTime: LocalDateTime = LocalDateTime.now()
) {


    fun checkPwd(pwdSha256Hex: String, timeStamp: Long): Boolean {
        //检查是否超时
        val betweenMinutes = DateUtil.between(Date(), Date(timeStamp), DateUnit.MINUTE)
        if (betweenMinutes > 30) {
            return false
        }
        val secret = "${email}${pwd}$timeStamp"
        val sha256Hex = DigestUtil.sha256Hex(secret)
        return pwdSha256Hex == sha256Hex
    }


}