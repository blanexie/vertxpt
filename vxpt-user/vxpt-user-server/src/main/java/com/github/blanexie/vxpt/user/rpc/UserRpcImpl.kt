package com.github.blanexie.vxpt.user.rpc

import com.github.blanexie.vxpt.user.api.UserRpc
import com.github.blanexie.vxpt.user.domain.service.UserService
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource


@RestController
class UserRpcImpl : UserRpc {

    @Resource
    lateinit var userService: UserService



}