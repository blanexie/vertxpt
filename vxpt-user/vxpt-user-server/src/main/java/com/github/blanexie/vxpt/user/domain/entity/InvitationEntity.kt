package com.github.blanexie.vxpt.user.domain.entity

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

class InvitationEntity(
    val id: Int,
    val code: String,   //邀请码
    val email: String, //受邀者邮箱
    val userId: Int,    //发出邀请函的用户id
    var receiveId: Int,   //接受邀请的用户
    val expireTime: LocalDateTime, //邀请函过期时间
    val createTime: LocalDateTime,
) {


    fun use(receiveId: Int) {
        //检查有无过期
        if (LocalDateTime.now().isAfter(expireTime)) {
            throw Error("邀请函已经过期， ID：${id}")
        }
        //检查有无已经使用
        if (receiveId != null) {
            throw Error("邀请函已经被使用， ID：${id}")
        }

        this.receiveId = receiveId
    }


}