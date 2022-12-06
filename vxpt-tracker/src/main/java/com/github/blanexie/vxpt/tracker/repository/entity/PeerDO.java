package com.github.blanexie.vxpt.tracker.repository.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PeerDO {

    private Integer id;
    private String infoHash;
    private String peerId;

    private String trackerId;

    private String authKey;
    private Integer userId;

    private String ip;

    private String ipv6;
    private Integer port;
    private Integer compact;

    private Long downloaded;
    private Long left;
    private Long uploaded;

    private String event;
    private Integer numwant;

    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
