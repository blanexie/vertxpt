package com.github.blanexie.vxpt.tracker.repository.mapper;

import com.github.blanexie.vxpt.tracker.repository.entity.PeerDO;

import java.util.List;

public interface PeerMapper {
    List<PeerDO> selectPeers(String infoHash);

    PeerDO selectById(Integer id);

    PeerDO selectByAuthKeyAndInfoHash(String authKey, String infoHash);

    void save(PeerDO peerDO);

    void update(PeerDO peerDO);

}
