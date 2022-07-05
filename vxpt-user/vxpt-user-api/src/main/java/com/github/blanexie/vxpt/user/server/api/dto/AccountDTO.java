package com.github.blanexie.vxpt.user.server.api.dto;

import java.time.LocalDateTime;


public class AccountDTO {

    private Integer id;

    /**
     * 关联用户
     */
    private Integer userId;

    /**
     * 角色
     */
    private String role;

    /**
     * 等级
     */
    private String level;

    /**
     * 做种数
     */
    private Integer uploadTorrentNum;

    /**
     * 活动种子数
     */
    private Integer activeTorrentNum;

    /**
     * 积分
     */
    private Integer points;

    /**
     * 真实下载量
     */
    private Long download;

    /**
     * 真实上传量
     */
    private Long upload;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
