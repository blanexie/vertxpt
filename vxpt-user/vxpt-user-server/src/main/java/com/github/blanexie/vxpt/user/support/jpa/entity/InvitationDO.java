package com.github.blanexie.vxpt.user.support.jpa.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 邀请函实体
 */
@Data
@Entity
@Table(schema = "vxpt-user", name = "invitation")
public class InvitationDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 邀请码
     */
    @Column(unique = true)
    private String code;

    /**
     * 受邀者邮箱
     */
    @Column(unique = true)
    private String email;

    /**
     * 发出邀请函的用户id
     */
    private Integer userId;

    @Column(unique = true)
    private Integer receiveId;

    /**
     * 邀请函过期时间
     */
    private LocalDateTime expireTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    @Column(nullable = false)
    private Integer status;


}
