package com.github.blanexie.vxpt.api.bbs.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ：xiezc
 * @date ：2022/11/9 7:30 PM
 */
@Data
public class ResUserDTO {

    private Integer id;

    private Integer userId;

    private String name;

    /**
     * 资源的md5，可以有效避免重复上传问题
     */
    private String hash;

    /**
     * 资源类型后缀，
     */
    private String suffix;

    /**
     * 资源类型后缀， 一般使用后缀， 大小限制为3M以内
     */
    private byte[] content;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
