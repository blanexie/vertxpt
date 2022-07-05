package com.github.blanexie.vxpt.bbs.server.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(schema = "vxpt-bbs")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class TorrentDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer postId;
    private String name;
    @Column(name = "`size`")
    private Long size;

    @Column(unique = true)
    private String infoHash;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name=" infoByte", columnDefinition="BLOB", nullable=true)
    private byte[] infoByte;

    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime = LocalDateTime.now();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime = LocalDateTime.now();
}
