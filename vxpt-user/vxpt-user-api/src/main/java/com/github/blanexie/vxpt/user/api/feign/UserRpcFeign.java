package com.github.blanexie.vxpt.user.api.feign;

import com.github.blanexie.vxpt.user.api.UserRpc;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "vxpt-user", contextId = "UserRpcFeign")
public interface UserRpcFeign extends UserRpc {
}
