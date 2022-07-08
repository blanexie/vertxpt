package com.github.blanexie.vxpt.user.domain.entity

import cn.hutool.core.date.DateUnit
import cn.hutool.core.date.DateUtil
import cn.hutool.crypto.digest.DigestUtil
import java.time.LocalDateTime
import java.util.*

class UserEntity(
    val id: Int,
    val nickName: String,
    val email: String,
    var pwd: String,
    val sex: Int, //1: 男  2: 女  0:未知
    var role: String="normalUser",  //角色. 默认普通用户
    val createTime: LocalDateTime = LocalDateTime.now(),
) {

    /**
     * 授予角色
     */
    fun updateRole(roleCode: String) {
        this.role = roleCode
    }

    /**
     * 修改密码
     */
    fun updatePwd(pwd: String) {
        this.pwd = pwd
    }

    /**
     * 检查密码
     */
    fun checkPwd(pwdSha256Hex: String): Boolean {
        val secret = "${email}${pwd}"
        val sha256Hex = DigestUtil.sha256Hex(secret)
        return pwdSha256Hex == sha256Hex
    }


}