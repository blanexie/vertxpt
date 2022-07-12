package com.github.blanexie.vxpt.auth.support.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * @author ：xiezc
 * @date ：2022/7/11 7:27 PM
 */
@Data
@Entity
@Table(schema = "vxpt_auth", name = "path")
public class PathDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String path;

    /**
     * 权限的code
     */
    private Set<String> permissions;

    /**
     * 角色
     */
    private Set<String> roles;

}
