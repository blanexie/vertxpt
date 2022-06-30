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
    private Integer addPoints;

    /**
     * 增加的真实下载量
     */
    private Long addDownload;

    /**
     * 增加的真实上传量
     */
    private Long addUpload;


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

    public Integer getAddPoints() {
        return addPoints;
    }

    public void setAddPoints(Integer addPoints) {
        this.addPoints = addPoints;
    }

    public Long getAddDownload() {
        return addDownload;
    }

    public void setAddDownload(Long addDownload) {
        this.addDownload = addDownload;
    }

    public Long getAddUpload() {
        return addUpload;
    }

    public void setAddUpload(Long addUpload) {
        this.addUpload = addUpload;
    }
}
