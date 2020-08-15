package com.zez.backend.controller;

import com.zez.backend.common.CommonResult;
import com.zez.backend.exception.NoTimeInputException;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author Purple
 * @Date 2020/8/13
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoTimeInputException.class)
    public CommonResult<Object> catchNoTimeInputException(NoTimeInputException e){
        return CommonResult.fail(404,e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public CommonResult<Object> catchRuntimeException(RuntimeException e){
        log.warn(e.getClass().toString()+":"+e.getMessage());
        return CommonResult.fail(500,e.getMessage());
    }

}
