package com.github.blanexie.vxpt.user.api;

import com.github.blanexie.vxpt.user.api.dto.AccountDTO;
import com.github.blanexie.vxpt.user.api.dto.PublishData;

public interface AccountRpc {

    void publish(PublishData publishData);

    /**
     * 获取数据
     * @param userId
     * @return
     */
    AccountDTO getByUserId(Integer userId);

}
