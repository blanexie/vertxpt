package com.github.blanexie.vxpt.user.support.jpa.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * 用户的账户信息,  积分, 等级, 权限等
 */
@Data
@Entity
@Table(schema = "vxpt-user", name = "account")
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
     * 当前用户拥有的邀请函数量,
     */
    private Integer invitationCount;

    /**
     * 等级
     */
    @Column(unique = true)
    private Integer level;

    /**
     * 做种数
     */
    private Integer completeCount;

    /**
     * 活动种子数
     */
    private Integer activeCount;

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

}
