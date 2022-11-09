package com.github.blanexie.vxpt.bbs.util

class Response(val code: Int, val data: Any?) {

    companion object {
        fun ok(): Response {
            return Response(200, null)
        }

        fun ok(data: Any): Response {
            return Response(200, data)
        }

        fun fail(code: Int, data: Any? = null): Response {
            return Response(code, data)
        }
    }
}