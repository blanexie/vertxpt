package com.github.blanexie.vxpt.bbs.entity;

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
import java.util.Map;

@ToString
@Getter
@Entity
@AllArgsConstructor
@Table(schema = "vxpt-bbs")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TorrentDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer postId;

    /**
     * 用户另外取的标题
     */
    @Column(nullable = false)
    private String title;

    @Column(name = "`size`", nullable = false)
    private Long size;

    @Column(unique = true, nullable = false)
    private String infoHash;
    @Column(nullable = false)
    private String infoName;
    @Column(nullable = false)
    private Long pieceLength;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<Map<String, Object>> files;

    @Lob
    @Column(name = " infoByte", columnDefinition = "BLOB", nullable = false)
    private byte[] infoByte;

    @Column(nullable = false)
    private Integer status;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;


}
