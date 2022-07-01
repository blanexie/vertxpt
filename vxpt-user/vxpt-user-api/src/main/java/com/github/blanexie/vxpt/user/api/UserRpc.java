package com.github.blanexie.vxpt.user.api;


import com.github.blanexie.vxpt.user.api.dto.UserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public interface UserRpc {

    /**
     * 登录
     *
     * @return
     */
    @PostMapping("/rpc/user/login")
    @ResponseBody
    UserDTO login(@RequestParam String email, @RequestParam String pwdSecret, @RequestParam Long timeStamp);

}
