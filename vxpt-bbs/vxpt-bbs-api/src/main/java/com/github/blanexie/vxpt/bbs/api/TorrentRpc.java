package com.github.blanexie.vxpt.bbs.api;

import com.github.blanexie.vxpt.bbs.api.dto.PostDTO;
import com.github.blanexie.vxpt.bbs.api.dto.TorrentDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TorrentRpc {

    /**
     * 上传帖子
     *
     * @param postDTO
     * @return
     */
    @PostMapping("/rpc/uploadPost")
    Integer uploadPost(@RequestBody PostDTO postDTO);

    /**
     * 上传种子
     *
     * @param torrentDTO
     * @return
     */
    @PostMapping("/rpc/uploadTorrent")
    Integer uploadTorrent(@RequestBody TorrentDTO torrentDTO);

    /**
     * 发布帖子
     *
     * @param postId
     * @return
     */
    @GetMapping("/rpc/publishPost")
    void publishPost(@RequestParam Integer postId, @RequestParam Integer userId);

    @GetMapping("/rpc/getPost")
    PostDTO getPost(@RequestParam Integer postId);

    @GetMapping("/rpc/findByInfoHash")
    TorrentDTO findByInfoHash(@RequestParam String infoHash);

    @PostMapping("/rpc/searchPost")
    List<PostDTO> searchPost();

}
