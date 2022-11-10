package com.github.blanexie.vxpt.api.user;


import com.github.blanexie.vxpt.api.user.dto.RegisterUserDTO;
import com.github.blanexie.vxpt.api.user.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

public interface UserRpc {

    @GetMapping("/userinfo/token")
    UserDTO userInfo(@RequestParam Integer userId);

    @GetMapping("/login")
    Integer login(@RequestParam String email, @RequestParam String pwdSecret);

    @PostMapping("/register")
    UserDTO register(@RequestBody RegisterUserDTO registerUserDTO);





}
