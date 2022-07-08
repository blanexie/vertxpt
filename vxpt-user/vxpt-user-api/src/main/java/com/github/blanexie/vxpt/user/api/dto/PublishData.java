package com.github.blanexie.vxpt.user.api.dto;

import lombok.Data;

import java.time.LocalDateTime;


/**
 * 上报数据
 */
@Data
public class PublishData {
    private Integer userId;
    private Integer addCompleteCount; //完种数
    private Integer addActiveCount; //活动种子数
    private Long addDownload; //真实下载量, 单位byte
    private Long addUpload; //真实上传量, 单位byte
}
