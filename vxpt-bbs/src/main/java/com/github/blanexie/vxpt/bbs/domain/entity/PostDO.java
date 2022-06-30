package com.github.blanexie.vxpt.bbs.domain.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 帖子对象
 */
@Entity
@Table(schema = "vxpt")
public class PostDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//: Int? = null,


    private String title;// : String, // 标题
    private String cover;// String, //封面
    private String category;//String, //分类

    private labels:MutableSet<Label>,

    @Column(columnDefinition = "TEXT")
    private String content;// : String,  //markdown 文本描述
    private Integer userId;//: Int, //用户的id
    private Integer status;//: Int = 0,
    private LocalDateTime createTime = LocalDateTime.now();
    private LocalDateTime updateTime = LocalDateTime.now();

}
