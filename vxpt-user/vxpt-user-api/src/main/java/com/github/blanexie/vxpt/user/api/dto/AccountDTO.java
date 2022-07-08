package com.github.blanexie.vxpt.user.api.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class AccountDTO {

    private Integer id;
    private Integer userId;//: Int,
    private Integer level;//: Int = 1, //等级
    private Integer points;//: Int = 0,//积分
    private Integer invitationCount;//: Int = 0,  //当前用户拥有的邀请函数量,
    private Integer completeCount;//: Int = 0, //完种数
    private Integer activeCount;//: Int = 0, //活动种子数
    private Long download;//: Long = 0, //真实下载量, 单位byte
    private Long upload;//: Long = 0, //真实上传量, 单位byte
    private LocalDateTime createTime;//: LocalDateTime = LocalDateTime.now(),

}
