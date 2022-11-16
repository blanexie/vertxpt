package com.github.blanexie.vxpt.bbs.post.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import cn.dev33.satoken.util.SaResult

/**
 * @author ：xiezc
 * @date ：2022/11/10 6:43 PM
 */
@RestController
@RequestMapping("/api")
class PingController {

    @GetMapping("/ping")
    fun ping(): SaResult {
        return SaResult.ok()
    }
}