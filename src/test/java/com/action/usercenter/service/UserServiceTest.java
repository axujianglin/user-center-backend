package com.action.usercenter.service;

import com.action.usercenter.pojo.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@SpringBootTest
class UserServiceTest {

    @Resource
    public UserService userService;
    @Resource
    public HttpServletRequest request;

    @Test
    void test1() {
        User user = new User();
        user.setUserName("test1");
        user.setUserAccount("");
        user.setAvatarUrl("");
        user.setGender(0);
        user.setPassword("xxx");
        user.setPhone("");
        user.setEmail("");
        boolean save = userService.save(user);
        Assertions.assertTrue(save);
    }

    @Test
    void register() {
        String userAccount = "xiao";
        String password = "";
        String checkPassword = "123456789";
        String planetNumber = "1234";
        String result = userService.register(userAccount, password, checkPassword, planetNumber);
        Assertions.assertEquals("必填字段为空", result);

        userAccount = "xi";
        password = "123456789";
        result = userService.register(userAccount, password, checkPassword, planetNumber);
        Assertions.assertEquals("用户长度过短，请重新输入", result);

        userAccount = "xiao";
        password = "123";
        result = userService.register(userAccount, password, checkPassword, planetNumber);
        Assertions.assertEquals("用户密码过短，请重新输入", result);

        password = "123456789";
        checkPassword = "123";
        result = userService.register(userAccount, password, checkPassword, planetNumber);
        Assertions.assertEquals("两次输入的密码不相同，请重新输入", result);

        userAccount = "xi  ao";
        checkPassword = "123456789";
        result = userService.register(userAccount, password, checkPassword, planetNumber);
        Assertions.assertEquals("账户名存在非法特殊字符，请重新输入", result);

        userAccount = "xiao";
        result = userService.register(userAccount, password, checkPassword, planetNumber);
        Assertions.assertEquals("用户名已存在，请重新输入", result);

        userAccount = "xiaoXu1";
        result = userService.register(userAccount, password, checkPassword, planetNumber);
        Assertions.assertEquals("", result);
    }

    @Test
    void login() {
        String userAccount = "";
        String password = "123456789";
        User user = userService.login(userAccount, password, request);
        Assertions.assertNull(user);

        userAccount = "xi";
        user = userService.login(userAccount,password, request);
        Assertions.assertNull(user);

        password = "1234";
        userAccount = "xiaoXu";
        user = userService.login(userAccount,password, request);
        Assertions.assertNull(user);

        userAccount = "xiao Xu";
        password = "123456789";
        user = userService.login(userAccount,password,request );
        Assertions.assertNull(user);

        userAccount = "xiaoXu";
        user = userService.login(userAccount,password, request);
        Assertions.assertNotEquals(null,user);
        User user1 = ((User) request.getSession().getAttribute("userInfo"));
        System.out.println(user1);
        Assertions.assertEquals(user,user1);
    }

    @Test
    void getSafetyUser() {

    }

    @Test
    void userLogOut() {
        /*boolean result = userService.userLogOut(1L);
        System.out.println(result);*/
        User user = userService.getById(1L);
        System.out.println(user);
    }
}