package com.github.blanexie.vxpt.user.rpc

import com.github.blanexie.vxpt.user.api.AccountRpc
import com.github.blanexie.vxpt.user.api.dto.PublishData
import com.github.blanexie.vxpt.user.domain.service.LoginAndPublishDataService
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource


@RestController
class AccountRpcImpl : AccountRpc {

    @Resource
    lateinit var userAndAccountService: LoginAndPublishDataService

    override fun publish(publishData: PublishData) {
        userAndAccountService.publishData(publishData)
    }

}