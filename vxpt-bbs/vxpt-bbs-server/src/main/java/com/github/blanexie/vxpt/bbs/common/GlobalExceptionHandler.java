package com.github.blanexie.vxpt.bbs.common;

import cn.dev33.satoken.util.SaResult;
import com.github.blanexie.vxpt.bbs.util.VxptException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 全局异常拦截
    @ExceptionHandler
    public SaResult handlerException(Exception e) {
        if (e instanceof VxptException) {
            log.error("错误信息", e);
            return SaResult.code(((VxptException) e).getCode()).setMsg(e.getMessage());
        }
        log.error("错误信息", e);
        return SaResult.error(e.getMessage());
    }

}