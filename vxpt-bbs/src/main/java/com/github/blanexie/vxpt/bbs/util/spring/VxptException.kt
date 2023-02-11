package com.github.blanexie.vxpt.bbs.util.spring

import java.lang.RuntimeException

class VxptException(val respCode: RespCode) : RuntimeException(respCode.message) {
}