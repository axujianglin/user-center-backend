package com.action.usercenter.service;

import com.action.usercenter.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 许江林
 * @DESCRIPTION: 用户服务
 * @since 2023-02-28
 */
public interface UserService extends IService<User> {


    /**
     * 用户注册
     *
     * @param userAccount   用户登录账号
     * @param password      用户密码
     * @param checkPassword 验证密码
     * @param planetNumber 星球编号
     * @return 返回是否注册成功的信息
     */
    String register(String userAccount, String password, String checkPassword,String planetNumber);

    /**
     * 用户登录功能
     *
     * @param userAccount 用户名
     * @param password    密码
     * @param request     此次请求体，用来设置session的相关信息
     * @return 脱敏后的用户信息
     */
    User login(String userAccount, String password, HttpServletRequest request);

    /**
     * 对数据库返回的用户信息进行脱敏处理
     * @param user 数据库返回的用户信息
     * @return 脱敏后的用户信息
     */
    User getSafetyUser(User user);

    /**
     * 用户注销功能
     * @param request 请求
     * @return 返回是否注销成功
     */
    boolean userLogOut(HttpServletRequest request);
}
