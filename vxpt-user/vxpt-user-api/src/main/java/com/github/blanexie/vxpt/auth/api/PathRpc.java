package com.github.blanexie.vxpt.auth.api;

import com.github.blanexie.vxpt.auth.api.dto.RoleDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

/**
 * @author ：xiezc
 * @date ：2022/7/12 5:08 PM
 */

public interface PathRpc {

    /**
     * 获取角色的权限
     *
     * @return
     */
    @PostMapping("/rpc/path/check/permission")
    @ResponseBody
    Boolean checkPath(@RequestParam String path, @RequestBody Set<String> permissionCodes);

}
