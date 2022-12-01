package com.github.blanexie.vxpt.user.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 邀请函实体
 */
@ToString
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(schema = "vxpt_user", name = "invitation")
@EntityListeners(AuditingEntityListener.class)
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
     * 发出邀请函的用户id
     */
    private Integer senderUserId;

    /**
     * 接受到邀请码的邮箱
     */
    @Column(unique = true)
    private String receiveEmail;


    @Column(unique = true)
    private Integer receiveUserId;

    /**
     * 邀请函过期时间
     */
    private LocalDateTime expireTime;

    /**
     * 0: 等待用户接受中， 1： 用户已接收注册  2：过期,
     */
    @Column(nullable = false)
    private Integer status = 0;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    private LocalDateTime updateTime;


    /**
     * 检查邀请函
     *
     * @param email 邀请码对应的注册邮箱
     * @return
     */
    public String check(String email) {
        if (status > 0) {
            return "邀请码已经不可用";
        }
        //检查有无过期
        if (LocalDateTime.now().isAfter(expireTime)) {
            this.status = 2;
            return "邀请码已经过期";
        }
        //检查有无已经使用
        if (receiveUserId != null) {
            this.status = 1;
            return "邀请码已经被使用";
        }
        //检查 注册邮箱和邀请码是否匹配
        if (!receiveEmail.equals(email)) {
            return "邀请码与注册邮箱不匹配";
        }
        return null;
    }

    /**
     * @param receiveId
     * @return 返回的是错误消息
     */
    public void use(Integer receiveId) {
        this.receiveUserId = receiveId;
        this.status = 1;
    }

}
