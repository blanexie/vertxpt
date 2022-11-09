package com.github.blanexie.vxpt.user.api;


import com.github.blanexie.vxpt.user.api.dto.RegisterUserDTO;
import com.github.blanexie.vxpt.user.api.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

public interface UserRpc {

    @GetMapping("/userinfo/token")
    UserDTO userInfo(@RequestParam Integer userId);

    @GetMapping("/login")
    Integer login(@RequestParam String email, @RequestParam String pwdSecret);

    @PostMapping("/register")
    UserDTO register(@RequestBody RegisterUserDTO registerUserDTO);





}
