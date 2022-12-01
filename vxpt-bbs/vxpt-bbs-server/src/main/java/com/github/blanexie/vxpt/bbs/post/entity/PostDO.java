package com.github.blanexie.vxpt.bbs.post.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@Table(schema = "vxpt_bbs")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class PostDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 标题
    @Column(nullable = false, length = 64)
    private String title;

    //封面
    @Column(nullable = false)
    private String cover;

    //分类
    @Column(nullable = false)
    private String category;

    //markdown 文本描述
    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    //用户的id
    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private Integer status;

    @CreatedDate
    private LocalDateTime createTime;

    @LastModifiedDate
    private LocalDateTime updateTime;


    public void publish() {
        this.status = 1;
    }

}
