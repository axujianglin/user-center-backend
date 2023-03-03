package com.action.usercenter.controller;


import com.action.usercenter.domain.request.UserLoginRequest;
import com.action.usercenter.domain.request.UserRegisterRequest;
import com.action.usercenter.pojo.User;
import com.action.usercenter.service.UserService;
import com.action.usercenter.util.api.RespBean;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.action.usercenter.util.UserConstant.ADMIN;
import static com.action.usercenter.util.UserConstant.USER_LOGIN_STATUS;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 许江林
 * @since 2023-02-28
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("用户注册功能")
    @PostMapping("/register")
    public RespBean<String> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            return RespBean.failed("用户注册失败");
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String password = userRegisterRequest.getPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetNumber = userRegisterRequest.getPlanetNumber();
        if (StringUtils.isAnyEmpty(userAccount, password, checkPassword, planetNumber)) {
            return RespBean.failed("用户注册失败");
        }
        String result = userService.register(userAccount, password, checkPassword, planetNumber);
        return RespBean.success("用户注册成功",null);
    }

    @ApiOperation("用户登录功能")
    @PostMapping("/login")
    public RespBean<User> login(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return RespBean.failed();
        }
        String userAccount = userLoginRequest.getUserAccount();
        String password = userLoginRequest.getPassword();
        if (StringUtils.isAnyEmpty(userAccount, password)) {
            return RespBean.failed("用户名和密码不能为空");
        }
        User user = userService.login(userAccount, password, request);
        return RespBean.success("用户登录成功", user);
    }

    @ApiOperation("获取当前用户态")
    @GetMapping("/current")
    public RespBean<User> getCurrent(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER_LOGIN_STATUS);
        if (user == null) {
            return RespBean.failed("用户未登录");
        }
        User user1 = userService.getById(user.getId());
        //TODO 需要检验一下用户是否合法，比如用户是否是正常状态，是否被删除
        User safetyUser = userService.getSafetyUser(user1);
        return RespBean.success(safetyUser);
    }


    @ApiOperation("用户查询功能")
    @GetMapping("/queryUser")
    public RespBean<List<User>> queryUser(String userName, HttpServletRequest request) {
        if (judgeAdmin(request)) {
            return RespBean.failed("您不具备查询权限");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(userName)) {
            queryWrapper.like("user_name", userName);
        }
        List<User> userList = userService.list(queryWrapper);
        List<User> collect = userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
        return RespBean.success(collect);
    }

    @ApiOperation("用户删除功能")
    @DeleteMapping("/deleteUser")
    public RespBean<String> deleteUser(Long userId, HttpServletRequest request) {
        if (judgeAdmin(request)) {
            return RespBean.failed("您不具备删除权限");
        }
        boolean result = userService.removeById(userId);
        return result ? RespBean.success("删除成功") : RespBean.failed("删除失败");
    }

    @ApiOperation("用户注销")
    @PostMapping("/userLogOut")
    public RespBean<Boolean> userLogOut(HttpServletRequest request) {
        return userService.userLogOut(request) ? RespBean.success("注销成功", true) : RespBean.failed("注销失败");
    }


    /**
     * 判断用户是否是管理员
     *
     * @param request 请求
     * @return 返回结果
     */
    public boolean judgeAdmin(HttpServletRequest request) {
        Object attribute = request.getSession().getAttribute(USER_LOGIN_STATUS);
        User user = ((User) attribute);
        return user == null || user.getUserRole() != ADMIN;
    }
}
