package com.github.blanexie.vxpt.user.server.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(schema = "vxpt-user")
public class UserDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String nickName;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false)
    private Integer sex; //1: 男  2: 女  0:未知
    /**
     * 角色
     */
    @Column(unique = true)
    private String role;

    /**
     * 邀请者
     */
    @Column(nullable = false)
    private Integer referencesUserId;

    @Column(unique = true)
    private String token;

    /**
     * token的过期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tokenExpireTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    @Column(nullable = false)
    private Integer status;

}
