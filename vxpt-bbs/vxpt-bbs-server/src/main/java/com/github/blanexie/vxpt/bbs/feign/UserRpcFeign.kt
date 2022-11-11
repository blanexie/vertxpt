package com.github.blanexie.vxpt.bbs.feign

import com.github.blanexie.vxpt.api.user.feign.UserRpc
import org.springframework.cloud.openfeign.FeignClient

@FeignClient("VXPT-USER")
interface UserRpcFeign : UserRpc {
}