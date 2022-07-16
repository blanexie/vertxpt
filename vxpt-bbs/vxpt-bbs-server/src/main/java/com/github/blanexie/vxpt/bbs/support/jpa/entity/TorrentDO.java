package com.github.blanexie.vxpt.bbs.support.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(schema = "vxpt-bbs")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class TorrentDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer postId;
    /**
     * 用户另外取的标题
     */
    private String title;

    @Column(name = "`size`")
    private Long size;

    @Column(unique = true)
    private String infoHash;
    private String infoName;
    private Long pieceLength;

    @Type(type = "json")
    @Column(columnDefinition = "jsonb")
    private List<Map<String, Object>> files;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = " infoByte", columnDefinition = "BLOB", nullable = true)
    private byte[] infoByte;

    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime = LocalDateTime.now();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime = LocalDateTime.now();
}
