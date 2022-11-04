package com.github.blanexie.vxpt.user.util

import cn.hutool.core.io.FileUtil
import cn.hutool.crypto.symmetric.AES
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.File

@Component
class TokenComponent {

    @Value("\${token.aes.key}")
    lateinit var tokenSecret: String

    @Value("\${token.expireSecond}")
    lateinit var expireSecond: String

    var aes: AES? = null

    fun getAES(): AES {
        if (aes != null) return aes!!
        aes = AES(tokenSecret.toByteArray())
        return aes!!
    }


    fun buildToken(userId: Int): String {
        return String(getAES().encrypt("${userId}|${System.currentTimeMillis()}"))
    }

    fun getExpireSecond(): Long {
        return expireSecond.toLong()
    }

}

