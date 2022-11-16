package com.github.blanexie.vxpt.bbs.common

import cn.dev33.satoken.stp.StpInterface
import com.github.blanexie.vxpt.api.user.feign.UserRpc
import org.springframework.stereotype.Component
import javax.annotation.Resource

@Component
class StpInterfaceImpl(@Resource val userRpc: UserRpc) : StpInterface {

    override fun getPermissionList(loginId: Any?, loginType: String?): MutableList<String> {
        if (loginId == null) {
            return mutableListOf()
        }
        val userInfo = userRpc.userInfo(loginId as Int?)
        return userInfo.roles.map { it.permissionCodes }
            .flatten().distinct().toMutableList()
    }

    override fun getRoleList(loginId: Any?, loginType: String?): MutableList<String> {
        if (loginId == null) {
            return mutableListOf()
        }
        val userInfo = userRpc.userInfo(loginId as Int?)
        return userInfo.roles.map { it.code }.toMutableList()
    }
}