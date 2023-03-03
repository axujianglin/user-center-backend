package com.action.usercenter.util.api;

import com.action.usercenter.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回对象
 * @author 许江江
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespBean<T> implements Serializable {
    /**
     *状态码
     */
    private Long statusCode;
    /**
     *提示信息
     */
    private String message;
    /**
     *返回的数据
     */
    private T data;
    /**
     * 具体问题描述
     */
    private String description;

    public RespBean(Long statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public RespBean(Long statusCode, String message, String description) {
        this.statusCode = statusCode;
        this.message = message;
        this.description = description;
    }

    /**
     * 成功返回结果，附加封装数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> RespBean<T> success(T data){
        return new RespBean<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.message(), data);
    }

    /**
     * 成功返回结果，附加封装数据和返回消息
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> RespBean<T> success(String message,T data){
        return new RespBean<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * @param errorCode 状态码对象
     * @param <T>
     * @return
     */
    public static <T> RespBean<T> failed(IErrorCode errorCode){
        return new RespBean<>(errorCode.getCode(),errorCode.message(),null);
    }

    /**
     * 失败返回结果
     * @param errorCode
     * @param message
     * @param <T>
     * @return
     */
    public static <T> RespBean<T> failed(IErrorCode errorCode,String message){
        return new RespBean<>(errorCode.getCode(),message,null);
    }

    /**
     * 失败返回结果
     * @param message
     * @param <T>
     * @return
     */
    public static <T> RespBean<T> failed(String message){
        return new RespBean<>(ResultCode.FAILED.getCode(), message,null);
    }

    /**
     * 失败返回结果
     * @param <T>
     * @return
     */
    public static <T> RespBean<T> failed(){
        return failed(ResultCode.FAILED);
    }

    public static <T> RespBean<T> failed(BusinessException exception){
        return new RespBean<T>(exception.getCode(),exception.getMessage(),exception.getDescription());
    }

    /**
     * 页面未找到返回结果
     * @param <T>
     * @return
     */
    public static <T> RespBean<T> validateFailed(){
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 页面未找到自定义返回信息
     * @param message
     * @param <T>
     * @return
     */
    public static <T> RespBean<T> validateFailed(String message){
        return new RespBean<>(ResultCode.VALIDATE_FAILED.getCode(), message,null);
    }

    /**
     * 账号未登录默认返回结果
     * @param <T>
     * @return
     */
    public static <T> RespBean<T> unauthorized(){
        return failed(ResultCode.UNAUTHORIZED);
    }

    /**
     * 账号未登录自定义返回信息
     * @param message
     * @param <T>
     * @return
     */
    public static <T> RespBean<T> unauthorized(String message){
        return new RespBean<>(ResultCode.UNAUTHORIZED.getCode(), message,null);
    }

    /**
     * 权限不足默认返回结果
     * @param <T>
     * @return
     */
    public static <T> RespBean<T> forbidden(){
        return failed(ResultCode.FORBIDDEN);
    }

    /**
     * 权限不足自定义返回结果
     * @param message
     * @param <T>
     * @return
     */
    public static <T> RespBean<T> forbidden(String message){
        return new RespBean<>(ResultCode.FORBIDDEN.getCode(),message,null);
    }

    /**
     * 链式编程，拓展通用返回对象的功能，实现键值对返回
     * @param describe 属性描述
     * @param data 属性值
     * @return 返回本身
     */
    public RespBean<T> data(String describe,T data){
        Map<String,Object> map = new HashMap<>();
        map.put(describe,data);
        return this;
    }








}
