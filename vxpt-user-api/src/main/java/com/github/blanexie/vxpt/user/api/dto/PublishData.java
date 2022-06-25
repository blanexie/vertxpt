package com.github.blanexie.vxpt.user.api.dto;

import java.time.LocalDateTime;


/**
 * 上报数据
 */
public class PublishData {

    /**
     * 关联用户
     */
    private Integer userId;

    /**
     * 做种数
     */
    private Integer uploadTorrentNum;

    /**
     * 活动种子数
     */
    private Integer activeTorrentNum;

    /**
     * 增加的积分
     */
    private Integer points;

    /**
     * 增加的真实下载量
     */
    private Long download;

    /**
     * 增加的真实上传量
     */
    private Long upload;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUploadTorrentNum() {
        return uploadTorrentNum;
    }

    public void setUploadTorrentNum(Integer uploadTorrentNum) {
        this.uploadTorrentNum = uploadTorrentNum;
    }

    public Integer getActiveTorrentNum() {
        return activeTorrentNum;
    }

    public void setActiveTorrentNum(Integer activeTorrentNum) {
        this.activeTorrentNum = activeTorrentNum;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Long getDownload() {
        return download;
    }

    public void setDownload(Long download) {
        this.download = download;
    }

    public Long getUpload() {
        return upload;
    }

    public void setUpload(Long upload) {
        this.upload = upload;
    }
}
