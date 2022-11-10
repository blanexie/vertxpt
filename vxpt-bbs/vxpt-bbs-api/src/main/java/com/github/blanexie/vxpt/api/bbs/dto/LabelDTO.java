package com.github.blanexie.vxpt.api.bbs.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ：xiezc
 * @date ：2022/11/9 7:29 PM
 */
@Data
public class LabelDTO {

    private Integer id;

    private Integer postId;

    private String label;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
