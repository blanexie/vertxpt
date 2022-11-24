package com.github.blanexie.vxpt.api.user.feign;


import com.github.blanexie.vxpt.api.user.dto.LoginUserDTO;
import com.github.blanexie.vxpt.api.user.dto.R;
import com.github.blanexie.vxpt.api.user.dto.RegisterUserDTO;
import com.github.blanexie.vxpt.api.user.dto.UserDTO;
import kotlin.Result;
import org.springframework.web.bind.annotation.*;

public interface UserRpc {

    @GetMapping("/userinfo")
    UserDTO userInfo(@RequestParam Integer userId);

    @GetMapping("/userInfoByEmail")
    UserDTO userInfoByEmail(@RequestParam String email);

    @GetMapping("/create/reset/pwd/token")
    R createResetPwdToken(@RequestParam String email);

    @GetMapping("/check/reset/pwd/token")
    R checkTokenAndResetPwd(@RequestParam String email, @RequestParam String token,
                            @RequestParam Long expireTime, @RequestParam String newPassword);

    @PostMapping("/login")
    Integer login(@RequestBody LoginUserDTO loginUserDTO);

    @PostMapping("/register")
    R register(@RequestBody RegisterUserDTO registerUserDTO);

}
