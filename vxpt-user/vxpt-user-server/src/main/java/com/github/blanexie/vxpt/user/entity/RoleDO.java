package com.github.blanexie.vxpt.user.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色实体
 */

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(schema = "vxpt_user", name = "role")
public class RoleDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限code
     */
    @Column(unique = true)
    private String code;

    /**
     * 权限
     */
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<String> permissionCodes;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    public void addPermission(String code) {
        this.permissionCodes.add(code);
    }

    public void removePermission(String code) {
        this.permissionCodes.remove(code);
    }

}
