package com.github.blanexie.vxpt.user.api;


import com.github.blanexie.vxpt.user.api.dto.UserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public interface UserRpc {


    @RequestMapping("/sso/userinfo")
    Object userinfo(@RequestParam Integer loginId);


}
