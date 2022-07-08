package com.github.blanexie.vxpt.auth.support.jpa.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 权限表
 */
@Data
@Entity
@Table(schema = "vxpt_auth",name = "permission")
public class PermissionDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 权限的code
     */
    @Column(unique = true)
    private String code;


    /**
     * 权限的说明
     */
    private String name;

    /**
     * 权限的说明
     */
    private String description;




}
