package com.github.blanexie.vxpt.bbs.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 资源管理类
 *
 * @author ：xiezc
 * @date ：2022/11/9 5:21 PM
 */
@ToString
@Getter
@Entity
@AllArgsConstructor
@Table(schema = "vxpt-bbs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 资源的md5，可以有效避免重复上传问题
     */
    @Column(nullable = false, unique = true)
    private String hash;

    /**
     * 资源类型后缀，
     */
    @Column(nullable = false)
    private String suffix;

    /**
     * 资源类型后缀， 一般使用后缀， 大小限制为3M以内
     */
    @Lob
    @Column(nullable = false, columnDefinition = "BLOB")
    private byte[] content;

    @Column(nullable = false)
    private Integer status;

    @CreatedDate
    private LocalDateTime createTime;

    @LastModifiedDate
    private LocalDateTime updateTime;

}
