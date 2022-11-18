package com.github.blanexie.vxpt.api.user.feign;


import com.github.blanexie.vxpt.api.user.dto.RegisterUserDTO;
import com.github.blanexie.vxpt.api.user.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

public interface UserRpc {

    @GetMapping("/userinfo")
    UserDTO userInfo(@RequestParam Integer userId);

    @GetMapping("/login")
    Integer login(@RequestParam String nickName, @RequestParam String pwdSecret, @RequestParam Long loginTime);

    @PostMapping("/register")
    Integer register(@RequestBody RegisterUserDTO registerUserDTO);

}
