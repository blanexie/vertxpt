package com.github.blanexie.vxpt.tracker.service

import com.github.blanexie.vxpt.tracker.service.dto.PeerEntity

interface PeerService {

    fun announce(peerEntity: PeerEntity): Map<String, Any>

    fun scrape()

    /**
     * 封禁某个用户的某个文件的上传，
     *
     */
    fun stopUpload(uid: Int?, infoHash: String?)

    /**
     * 封禁某个用户的某个文件的上传，
     *
     */
    fun stopDownload(uid: Int?, infoHash: String?)


}