package com.action.usercenter.util.api;

/**
 * 通用Api接口返回对象
 * @author 许江江
 */
public interface IErrorCode {
    /**
     * 返回状态码
     * @return
     */
    long getCode();

    /**
     * 返回信息
     * @return
     */
    String message();
}
