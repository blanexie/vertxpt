package com.github.blanexie.vxpt.user.api;

import com.github.blanexie.vxpt.user.api.dto.PublishData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AccountRpc {


    @PostMapping("/rpc/account/publish")
    void publish(@RequestBody PublishData publishData);

}
