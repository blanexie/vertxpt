package com.github.blanexie.vxpt.api.user.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

    private Integer id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限code
     */
    private String code;

    /**
     * 权限
     */
    private List<String> permissionCodes;

}
