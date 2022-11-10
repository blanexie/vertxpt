package com.github.blanexie.vxpt.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer id;

    private String nickName;

    private String email;

    private Integer sex; //1: 男  2: 女  0:未知

    /**
     * 当前用户包含的所有角色。 包含子角色
     */
    private List<RoleDTO> roles;


}
