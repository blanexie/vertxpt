package com.github.blanexie.vxpt.bbs.util.spring


class WebResp(val code: Int, val message: String, val data: Any?) {
    companion object {
        fun success(data: Any): WebResp {
            return WebResp(200, "", data)
        }

        fun success(): WebResp {
            return WebResp(200, "", null)
        }

        fun fail(code: Int, message: String): WebResp {
            return WebResp(code, message, null)
        }
    }
}
