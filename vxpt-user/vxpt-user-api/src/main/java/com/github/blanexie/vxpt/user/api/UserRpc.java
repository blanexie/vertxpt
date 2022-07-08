package com.github.blanexie.vxpt.user.api;


import com.github.blanexie.vxpt.user.api.dto.UserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public interface UserRpc {

    /**
     * 根据token获取当前登录的用户信息
     *
     * @return
     */
    @PostMapping("/rpc/user/token")
    @ResponseBody
    UserDTO login(@RequestParam String token);

}
