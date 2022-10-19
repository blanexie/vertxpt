package com.github.blanexie.vxpt.user.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 邀请函实体
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(schema = "vxpt_user", name = "invitation")
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
     * @param receiveId
     * @return 返回的是错误消息
     */
    public String use(Integer receiveId) {
        if (status > 0) {
            return "邀请函已经状态不对，无法使用； ID:" + id;
        }
        //检查有无过期
        if (LocalDateTime.now().isAfter(expireTime)) {
            this.status = 2;
            return "邀请函已经过期， ID：" + id;
        }
        //检查有无已经使用
        if (receiveId != null) {
            this.status = 1;
            return "邀请函已经被使用， ID：" + id;
        }
        this.receiveId = receiveId;
        this.status = 1;
        return null;
    }

}
