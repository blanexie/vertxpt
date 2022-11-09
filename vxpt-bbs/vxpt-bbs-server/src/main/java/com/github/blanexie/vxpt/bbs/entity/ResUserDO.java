package com.github.blanexie.vxpt.bbs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 资源用户对应关系表
 *
 * @author ：xiezc
 * @date ：2022/11/9 7:13 PM
 */
@ToString
@Getter
@Entity
@AllArgsConstructor
@Table(schema = "vxpt-bbs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResUserDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private String md5;

    /**
     * 文件的真实名称， 包含后缀
     */
    private String name;

    @Column(nullable = false)
    private Integer status = 0;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
