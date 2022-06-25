package com.github.blanexie.vxpt.user.domain.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * 用户的账户信息,  积分, 等级, 权限等
 */
@Entity
@Table(schema = "vxpt")
public class AccountDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 关联用户
     */
    @Column(unique = true)
    private Integer userId;

    /**
     * 角色
     */
    @Column(unique = true)
    private String role;

    /**
     * 等级
     */
    @Column(unique = true)
    private String level;


    /**
     * 做种数
     */
    private Integer uploadTorrentNum;

    /**
     * 活动种子数
     */
    private Integer activeTorrentNum;

    /**
     * 积分
     */
    @Column(unique = true)
    private Integer points;

    /**
     * 真实下载量
     */
    private Long download;

    /**
     * 真实上传量
     */
    private Long upload;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    @Column(nullable = false)
    private Integer status;
}
