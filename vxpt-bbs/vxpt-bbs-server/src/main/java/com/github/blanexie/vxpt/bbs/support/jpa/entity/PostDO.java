package com.github.blanexie.vxpt.bbs.support.jpa.entity;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 帖子对象
 */
@Data
@Entity
@Table(schema = "vxpt-bbs")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class PostDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//: Int? = null,
    private String title;// : String, // 标题
    private String cover;// String, //封面
    private String category;//String, //分类

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<String> labels;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;// : String,  //markdown 文本描述
    private Integer userId;//: Int, //用户的id
    private Integer status;//: Int = 0,
    private LocalDateTime createTime = LocalDateTime.now();
    private LocalDateTime updateTime = LocalDateTime.now();

}
