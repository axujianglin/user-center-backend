package com.action.usercenter.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.action.usercenter.exception.BusinessException;
import com.action.usercenter.pojo.User;
import com.action.usercenter.mapper.UserMapper;
import com.action.usercenter.service.UserService;
import com.action.usercenter.util.MD5Util;
import com.action.usercenter.util.api.ResultCode;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.action.usercenter.util.UserConstant.USER_LOGIN_STATUS;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 许江林
 * @since 2023-02-28
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {



    @Override
    public String register(String userAccount, String password, String checkPassword, String planetNumber) {
        if (StringUtils.isAnyEmpty(userAccount, password, checkPassword)) {
            throw new BusinessException(ResultCode.PARAM_EXCEPTION,"字段不允许为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ResultCode.PARAM_EXCEPTION,"用户账号长度不符合要求，必须不小于4");
        }
        if (password.length() < 8) {
            throw new BusinessException(ResultCode.PARAM_EXCEPTION,"用户密码长度不符合要求，必须不小于8");
        }
        if (!password.equals(checkPassword)) {
            throw new BusinessException(ResultCode.PARAM_EXCEPTION,"两次密码不匹配，请重新输入");
        }
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Matcher matcher = Pattern.compile(regEx).matcher(userAccount);
        if (matcher.find()) {
            throw new BusinessException(ResultCode.PARAM_EXCEPTION,"用户账号中存在特殊字符，请重新输入");
        }
        if(planetNumber.length() < 2 || planetNumber.length() > 5){
            throw new BusinessException(ResultCode.PARAM_EXCEPTION,"星球编号不存在，请重新输入");
        }
        Integer count = query().eq("user_account", userAccount).count();
        if (count > 0) {
            throw new BusinessException(ResultCode.PARAM_EXCEPTION,"用户名已存在，请重新输入");
        }
        Integer count1 = query().eq("planet_number", planetNumber).count();
        if (count1 > 0) {
            throw new BusinessException(ResultCode.PARAM_EXCEPTION,"星球编号已存在，请重新输入");
        }
        User user = new User();
        user.setUserAccount(userAccount);
        user.setGender(0);
        user.setPassword(MD5Util.md5(password));
        user.setPlanetNumber(planetNumber);
        boolean save = save(user);
        if (!save) {
            throw new BusinessException(ResultCode.SYSTEM_EXCEPTION,"用户注册失败");
        }
        return "";
    }

    @Override
    public User login(String userAccount, String password, HttpServletRequest request) {
        if (StringUtils.isAnyEmpty(userAccount, password)) {
            //TODO 将这些统一返回信息改造成统一异常处理器抛出异常
            throw new BusinessException(ResultCode.PARAM_EXCEPTION,"请求参数为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ResultCode.PARAM_EXCEPTION,"用户账号长度不符合要求，必须不小于4");
        }
        if (password.length() < 8) {
            throw new BusinessException(ResultCode.PARAM_EXCEPTION,"用户密码长度不符合要求，必须不小于8");
        }
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Matcher matcher = Pattern.compile(regEx).matcher(userAccount);
        if (matcher.find()) {
            throw new BusinessException(ResultCode.PARAM_EXCEPTION,"用户账号中存在特殊字符，请重新输入");
        }
        User user = query().eq("user_account", userAccount).eq("password", MD5Util.md5(password)).one();
        if(user == null){
            throw new BusinessException(ResultCode.PARAM_EXCEPTION,"账户或密码错误，没有查询到指定用户");
        }
        User safetyUser = getSafetyUser(user);
        request.getSession().setAttribute(USER_LOGIN_STATUS,safetyUser);
        return safetyUser;
    }

    @Override
    public User getSafetyUser(User user) {
        if(user == null){
            return null;
        }
        User safetyUser = new User();
        safetyUser.setId(user.getId());
        safetyUser.setUserName(user.getUserName());
        safetyUser.setUserAccount(user.getUserAccount());
        safetyUser.setAvatarUrl(user.getAvatarUrl());
        safetyUser.setGender(0);
        safetyUser.setUserRole(user.getUserRole());
        safetyUser.setPhone(user.getPhone());
        safetyUser.setEmail(user.getEmail());
        safetyUser.setPlanetNumber(user.getPlanetNumber());
        return safetyUser;
    }

    @Override
    public boolean userLogOut(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_LOGIN_STATUS);
        return true;
    }
}
