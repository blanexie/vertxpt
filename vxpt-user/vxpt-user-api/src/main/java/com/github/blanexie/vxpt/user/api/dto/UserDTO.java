package com.github.blanexie.vxpt.user.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    private Integer id;

    private String nickName;

    private String email;

    private Integer sex; //1: 男  2: 女  0:未知

}
