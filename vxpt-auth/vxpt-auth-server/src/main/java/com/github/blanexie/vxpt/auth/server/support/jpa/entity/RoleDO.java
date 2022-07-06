package com.github.blanexie.vxpt.auth.server.support.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 角色表， 包含角色的权限关系
 */
@Data
@Entity
@Table(schema = "vxpt-auth")
public class RoleDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色的code
     */
    @Column(unique = true)
    private String code;


    /**
     * 角色的说明
     */
    private String description;


    /**
     * 角色拥有的权限
     */
    private List<String>  authorities;

    /**
     * 角色拥有的子角色， 表示当前角色拥有子角色的所有权限
     */
    private List<String> roles;


}
