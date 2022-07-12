package com.github.blanexie.vxpt.auth.rpc

import com.github.blanexie.vxpt.auth.api.RoleRpc
import com.github.blanexie.vxpt.auth.api.dto.RoleDTO
import com.github.blanexie.vxpt.auth.domain.service.RoleService
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

@RestController
class RoleRpcController : RoleRpc {

    @Resource
    lateinit var roleService: RoleService

    override fun findByCode(@RequestParam code: String): RoleDTO? {
        return roleService.findByRoleCode(code)
    }


}