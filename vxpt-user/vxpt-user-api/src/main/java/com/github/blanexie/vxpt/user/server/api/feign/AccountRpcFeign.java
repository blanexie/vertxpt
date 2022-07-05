package com.github.blanexie.vxpt.user.server.api.feign;

import com.github.blanexie.vxpt.user.server.api.AccountRpc;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "vxpt-user", contextId = "AccountRpcFeign")
public interface AccountRpcFeign extends AccountRpc {
}
