package com.github.blanexie.vxpt.auth.support.jpa.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

/**
 * 角色表， 包含角色的权限关系
 */
@Data
@Entity
@Table(schema = "vxpt_auth", name = "role")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class RoleDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色的code
     */
    @Column(unique = true)
    private String code;


    private String name;

    /**
     * 角色拥有的权限
     */

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<String> permissions;

    /**
     * 角色拥有的子角色， 表示当前角色拥有子角色的所有权限
     */

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<String> roles;


}
