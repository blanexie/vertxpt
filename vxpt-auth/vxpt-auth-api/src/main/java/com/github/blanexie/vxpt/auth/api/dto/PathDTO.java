package com.github.blanexie.vxpt.auth.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class PathDTO {

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
