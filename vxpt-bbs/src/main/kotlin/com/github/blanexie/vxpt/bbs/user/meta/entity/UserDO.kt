package com.github.blanexie.vxpt.bbs.user.meta.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import lombok.NoArgsConstructor
import java.time.LocalDateTime


@TableName("user")
class UserDO(
    @TableId(type = IdType.AUTO)
    var id: Int,
    var email: String,
    var nickName: String,
    var password: String,
    var coverImg: String,
    var sex: Int,
    var role: String,
    var status: Int,
    var createTime: String,
    var updateTime: String,
) {
}