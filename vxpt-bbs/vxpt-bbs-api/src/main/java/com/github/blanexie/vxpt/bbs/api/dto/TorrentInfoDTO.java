package com.github.blanexie.vxpt.bbs.api.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 种子的基本信息， 不包含种子体
 * @author ：xiezc
 * @date ：2022/11/8 7:19 PM
 */
@Data
public class TorrentInfoDTO {

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
    private Integer status;



}
