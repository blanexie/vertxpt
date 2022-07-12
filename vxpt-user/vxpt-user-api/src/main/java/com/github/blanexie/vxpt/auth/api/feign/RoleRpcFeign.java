package com.github.blanexie.vxpt.auth.api.feign;

import com.github.blanexie.vxpt.auth.api.RoleRpc;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "vxpt-user", contextId = "roleRpcFeign")
public interface RoleRpcFeign extends RoleRpc {
}
