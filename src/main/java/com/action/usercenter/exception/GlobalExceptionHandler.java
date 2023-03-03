package com.action.usercenter.exception;

import com.action.usercenter.util.api.RespBean;
import com.action.usercenter.util.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: 许江江
 * @DATE: 2023/3/2
 * @DESCRIPTION: 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public RespBean<String> handlerException(Exception e){
        log.error("系统产生异常：",e);
        return RespBean.failed(ResultCode.FAILED);
    }

    @ExceptionHandler(BusinessException.class)
    public RespBean<String> handlerBusinessException(BusinessException exception){
        log.error("产生自定义异常 ："+exception.getMessage(),exception);
        return RespBean.failed(exception);
    }
}
