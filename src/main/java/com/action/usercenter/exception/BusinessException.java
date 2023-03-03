package com.action.usercenter.exception;

import com.action.usercenter.util.api.IErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: 许江江
 * @DATE: 2023/3/2
 * @DESCRIPTION: 自定义运行时异常类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    private long code;

    private String message;

    private String description;

    public BusinessException(long code,String message,String description){
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(IErrorCode errorCode,  String description){
        super(errorCode.message());
        this.message = errorCode.message();
        this.code = errorCode.getCode();
        this.description = description;
    }
}
