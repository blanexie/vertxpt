package com.github.blanexie.vxpt.api.user.dto
/**
 *
 * @author ：xiezc
 * @date   ：2022/11/18 5:43 PM
 */
class LoginUserDTO(
    val password: String, val loginTime: Long, val nickName: String? = null, val email: String? = null
)