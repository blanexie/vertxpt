package com.github.blanexie.vxpt.bbs.api.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class TorrentDTO {

    private Integer id;

    private Integer postId;

    /**
     * 用户另外取的标题
     */
    private String title;

    private Long size;
    private String infoHash;
    private String infoName;
    private Long pieceLength;
    private List<Map<String, Object>> files;
    private byte[] infoByte;
    private Integer status;

}
