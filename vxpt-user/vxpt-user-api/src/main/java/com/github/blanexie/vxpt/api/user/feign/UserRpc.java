package com.github.blanexie.vxpt.api.user.feign;


import com.github.blanexie.vxpt.api.user.dto.LoginUserDTO;
import com.github.blanexie.vxpt.api.user.dto.Resp;
import com.github.blanexie.vxpt.api.user.dto.RegisterUserDTO;
import com.github.blanexie.vxpt.api.user.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

public interface UserRpc {

    @GetMapping("/userinfo")
    UserDTO userInfo(@RequestParam Integer userId);

    @GetMapping("/userInfoByEmail")
    UserDTO userInfoByEmail(@RequestParam String email);

    @GetMapping("/create/reset/pwd/token")
    Resp createResetPwdToken(@RequestParam String email, @RequestParam Long expireTime);

    @GetMapping("/check/reset/pwd/token")
    Resp checkTokenAndResetPwd(@RequestParam String email, @RequestParam String token,
                            @RequestParam Long expireTime, @RequestParam String newPassword);

    @PostMapping("/login")
    Integer login(@RequestBody LoginUserDTO loginUserDTO);

    @PostMapping("/register")
    Resp register(@RequestBody RegisterUserDTO registerUserDTO);

}
