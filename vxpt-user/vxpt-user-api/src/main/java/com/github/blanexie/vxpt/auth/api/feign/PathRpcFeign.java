package com.github.blanexie.vxpt.auth.api.feign;

import com.github.blanexie.vxpt.auth.api.RoleRpc;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author ：xiezc
 * @date ：2022/7/12 5:10 PM
 */
@FeignClient(name = "vxpt-user", contextId = "pathRpcFeign")
public interface PathRpcFeign extends RoleRpc {
}
