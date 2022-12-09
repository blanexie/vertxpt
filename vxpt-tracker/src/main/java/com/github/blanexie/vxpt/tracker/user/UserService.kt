package com.github.blanexie.vxpt.tracker.user

import com.github.blanexie.vxpt.ioc.annotation.Bean

@Bean
class UserService {

    /**
     * 检查用户有无权限下载这个种子
     */
    fun checkAuthAndInfoHash(authKey: String, infoHash: String): User {
        TODO("Not yet implemented")
    }


}