package com.github.blanexie.vxpt.auth.api;

import com.github.blanexie.vxpt.auth.api.dto.RoleDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public interface RoleRpc {

    /**
     * 获取角色的权限
     *
     * @return
     */
    @PostMapping("/rpc/role/findByCode")
    @ResponseBody
    RoleDTO findByCode(@RequestParam String code);

}