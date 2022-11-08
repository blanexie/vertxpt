package com.github.blanexie.vxpt.bbs.api.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class TorrentDTO extends TorrentInfoDTO{
    private byte[] infoByte;
}
