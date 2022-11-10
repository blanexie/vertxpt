package com.github.blanexie.vxpt.api.bbs.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostDTO {

    private Integer id;//: Int? = null,
    private String title;// : String, // 标题
    private String cover;// String, //封面
    private String category;//String, //分类

    private List<String> labels;

    private String content;// : String,  //markdown 文本描述
    private Integer userId;//: Int, //用户的id

    private List<TorrentInfoDTO> torrentDTOS;


}
