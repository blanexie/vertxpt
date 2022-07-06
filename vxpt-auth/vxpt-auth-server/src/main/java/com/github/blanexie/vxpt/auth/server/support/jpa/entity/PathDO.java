package com.github.blanexie.vxpt.auth.server.support.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 路径资源
 */
@Data
@Entity
@Table(schema = "vxpt-auth")
public class PathDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 属于那个服务
     */
    private String service;

    /**
     * 请求的路径
     */
    private String path;

    /**
     * 请求资源需要的权限
     */
    private List<String> authority;

    /**
     * 资源的描述
     */
    private String description;


}
