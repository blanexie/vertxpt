package com.github.blanexie.vxpt.bbs.util


/**
 * 通用全局异常
 * @author ：xiezc
 * @date   ：2022/11/16 7:38 PM
 */
class VxptException(val code: Int, errorMsg: String, throws: Throwable? = null) : RuntimeException(errorMsg, throws) {

    constructor(code: Int, errorMsg: String) : this(code, errorMsg, null)
}