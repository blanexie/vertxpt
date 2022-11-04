package com.github.blanexie.vxpt.bbs.jpa.entity;


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

/**
 * 帖子对象
 */
@ToString
@Getter
@Entity
@AllArgsConstructor
@Table(schema = "vxpt-bbs")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//: Int? = null,

    @Column(nullable = false, length = 64)
    private String title;// : String, // 标题

    @Column(nullable = false)
    private String cover;// String, //封面
    @Column(nullable = false)
    private String category;//String, //分类

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<String> labels;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;// : String,  //markdown 文本描述

    @Column(nullable = false)
    private Integer userId; //: Int, //用户的id

    @Column(nullable = false)
    private Integer status;//: Int = 0,

    @CreatedDate
    private LocalDateTime createTime;

    @LastModifiedDate
    private LocalDateTime updateTime;


    public void publish() {
        this.status = 1;
    }

}
