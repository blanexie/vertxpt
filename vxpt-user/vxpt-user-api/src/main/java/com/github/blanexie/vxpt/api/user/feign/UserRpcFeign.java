package com.github.blanexie.vxpt.api.user.feign;

import com.github.blanexie.vxpt.api.user.UserRpc;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "vxpt-user", contextId = "UserRpcFeign")
public interface UserRpcFeign extends UserRpc {
}
