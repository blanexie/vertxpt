package com.github.blanexie.vxpt.auth.rpc

import com.github.blanexie.vxpt.auth.api.PathRpc
import com.github.blanexie.vxpt.auth.domain.service.PathService
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

/**
 *
 * @author ：xiezc
 * @date   ：2022/7/12 5:11 PM
 */
@RestController
class PathRpcController : PathRpc {

    @Resource
    lateinit var pathService: PathService

    override fun checkPath(@RequestParam path: String, @RequestBody permissionCodes: MutableSet<String>): Boolean {
        return pathService.checkPath(path, permissionCodes)
    }
}