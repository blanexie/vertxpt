package com.github.blanexie.vxpt.user.rpc

import com.github.blanexie.vxpt.user.api.AccountRpc
import com.github.blanexie.vxpt.user.api.dto.PublishData
import com.github.blanexie.vxpt.user.domain.service.AccountService
import com.github.blanexie.vxpt.user.domain.service.UserService
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource


@RestController
class AccountRpcImpl : AccountRpc {

    @Resource
    lateinit var accountService: AccountService

    override fun publish(publishData: PublishData) {
        accountService.addAccountData(
            publishData.userId,
            publishData.addDownload,
            publishData.addUpload,
            publishData.addActiveCount,
            publishData.addCompleteCount
        )
    }

}