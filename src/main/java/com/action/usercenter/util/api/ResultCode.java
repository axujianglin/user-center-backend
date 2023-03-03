package com.action.usercenter.util.api;

/**
 * @author 许江江
 */

public enum ResultCode implements IErrorCode{
    /**
     * 操作成功
     */
    SUCCESS(200,"操作成功"),
    /**
     * 操作失败
     */
    FAILED(500,"操作失败"),
    /**
     * 未找到页面
     */
    VALIDATE_FAILED(404,"未找到页面"),
    /**
     * 暂未登录或者token已经过期
     */
    UNAUTHORIZED(401,"暂未登录或token已经过期"),
    /**
     * 没有访问权限
     */
    FORBIDDEN(403,"没有访问权限"),
    /**
     * 触发自定义异常
     */
    CUSTOM_EXCEPTION(501,"触发自定义异常"),
    /**
     * 读取到的excel值为空
     */
    EXCEL_EXCEPTION(502,"读取到的excel值为空"),
    /**
     * 登录失败
     */
    LOGIN_EXCEPTION(601,"登录失败"),

    PARAM_EXCEPTION(40001,"参数异常"),

    SYSTEM_EXCEPTION(50000,"系统异常");


    private final long code;
    private final String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
