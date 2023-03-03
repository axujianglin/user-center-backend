package com.action.usercenter.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: 许江江
 * @DATE: 2023/3/1
 * @DESCRIPTION: 用户登录前端传递类
 */
@Data
public class UserLoginRequest implements Serializable {
    private String userAccount;
    private String password;
}
