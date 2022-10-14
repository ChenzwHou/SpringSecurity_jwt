package com.example.exception;

import com.example.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
@Slf4j
public class GlobleException {
    //500
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = RuntimeException.class)
    public R exception(RuntimeException e){
        log.error("系统运行时异常---->",e.getMessage());
         return R.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = AccessDeniedException.class)
    public R exception(AccessDeniedException e){
        log.error("权限不足---->",e.getMessage());
        return R.fail("权限不足！！！");
    }


}
