package com.github.blanexie.vxpt.auth.api.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class RoleDTO {

    /**
     * 属于那个服务
     */
    private String code;

    /**
     * 请求的路径
     */
    private String name;

    /**
     * 角色拥有的权限， 子角色的权限也包含在内
     */
    private Set<PermissionDTO> permissions;

}
