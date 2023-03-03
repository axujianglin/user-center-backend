package com.action.usercenter;

import com.action.usercenter.util.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class UserCenterApplicationTests {

    @Test
    void contextLoads() {

    }

    /**
     * 测试MD5加密用户密码是否可行
     */
    @Test
    void testMd5() {
        String password = "abcd";
        String s = MD5Util.md5(password);
        System.out.println(s);
    }

    @Test
    void testIsSpecial() {
        String str = "xi ao";
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        System.out.println(m.find());
    }
}
