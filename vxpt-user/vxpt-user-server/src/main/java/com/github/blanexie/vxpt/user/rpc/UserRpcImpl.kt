package com.github.blanexie.vxpt.user.rpc

import com.github.blanexie.vxpt.user.api.UserRpc
import com.github.blanexie.vxpt.user.api.dto.UserDTO
import com.github.blanexie.vxpt.user.domain.service.LoginAndPublishDataService
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource


@RestController
class UserRpcImpl : UserRpc {

    @Resource
    lateinit var userAndAccountService: LoginAndPublishDataService

    override fun login(token: String): UserDTO? {
        return userAndAccountService.login(email, pwdSecret, timeStamp)
    }


}