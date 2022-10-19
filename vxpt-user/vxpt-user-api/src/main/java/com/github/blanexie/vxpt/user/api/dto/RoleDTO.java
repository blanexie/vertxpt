package com.github.blanexie.vxpt.user.api.dto;

import lombok.*;

import java.util.List;

@Getter
@ToString
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
