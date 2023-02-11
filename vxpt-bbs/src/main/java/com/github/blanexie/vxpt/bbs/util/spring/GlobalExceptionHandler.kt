package com.github.blanexie.vxpt.bbs.util.spring

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest


@ControllerAdvice
@ResponseBody
class GlobalExceptionHandler {

    val logger: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(value = [Exception::class])
    fun exceptionHandler(request: HttpServletRequest, e: Exception): WebResp {
        if (e is VxptException) {
            logger.error("全局异常处理器拦截了", e)
            return WebResp.fail(e.respCode.code, e.respCode.message)
        }
        logger.error("全局异常处理器拦截了", e)
        return WebResp.fail(RespCode.SERVER_ERROR.code, e.message ?: "")
    }
}
