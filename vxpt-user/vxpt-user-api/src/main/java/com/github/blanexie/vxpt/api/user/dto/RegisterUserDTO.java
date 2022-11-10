package com.github.blanexie.vxpt.api.user.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDTO {

    private Integer userId;

    private Integer invitationId;
    private String code;
    private String nickName;

    private String email;

    private Integer sex; //1: 男  2: 女  0:未知

    private String pwd;

    /**
     * 当前用户包含的所有角色。 包含子角色
     */
    private List<RoleDTO> roles;
}
