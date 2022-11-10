package com.github.blanexie.vxpt.bbs.controller;

import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：xiezc
 * @date ：2022/11/10 6:43 PM
 */
@RestController
@RequestMapping
public class IUserController {

    @GetMapping("/api/test")
    public SaResult test() {
        return SaResult.ok();
    }

}
