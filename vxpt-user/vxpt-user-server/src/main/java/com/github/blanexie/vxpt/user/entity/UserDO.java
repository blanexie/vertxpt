package com.github.blanexie.vxpt.user.entity;

import cn.hutool.crypto.digest.DigestUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@ToString
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(schema = "vxpt_user", name = "user")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
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

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<String> roles;

    /**
     * 邀请函id
     */
    @Column(nullable = false)
    private Integer invitationId;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 0： 正常用户  -1： 删除用户
     */
    private Integer status;

    /**
     * 授予角色
     */
    public void addRole(String roleCode) {
        this.roles.add(roleCode);
    }

    /**
     * 删除角色
     */
    public Boolean remove(String roleCode) {
        return this.roles.remove(roleCode);
    }

    /**
     * 修改密码
     */
    public void updatePwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * 检查密码
     */
    public Boolean checkPwd(String pwdMd5Hex, Long loginTime) {
        if (loginTime < System.currentTimeMillis() - 10 * 60 * 1000 || loginTime > System.currentTimeMillis()) {
            //10分钟有效期
            return false;
        }
        String secret = this.nickName + this.pwd + loginTime;
        String md5Hex = DigestUtil.md5Hex(secret);
        return pwdMd5Hex.equals(md5Hex);
    }

}
