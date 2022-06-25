package com.github.blanexie.vxpt.user.api;


import com.github.blanexie.vxpt.user.api.dto.UserDTO;

public interface UserRpc {

    /**
     * 获取Token对应的用户
     *
     * @param token
     * @return
     */
    UserDTO getByToken(String token);

}
