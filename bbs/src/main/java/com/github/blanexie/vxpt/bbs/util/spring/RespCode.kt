package com.github.blanexie.vxpt.bbs.util.spring

enum class RespCode(val code: Int, var message: String) {

    Not_Found(404, "NotFound"),
    SERVER_ERROR(500,"服务器错误")


}