package com.github.blanexie.vxpt.bbs.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Getter
@Entity
@AllArgsConstructor
@Table(schema = "vxpt-bbs", uniqueConstraints = {@UniqueConstraint(columnNames = {"postId", "label"})})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LabelDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer postId;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private Integer status;

    @CreatedDate
    private LocalDateTime createTime;

    @LastModifiedDate
    private LocalDateTime updateTime;

}
