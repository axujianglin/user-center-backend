package com.action.usercenter.config;

import com.action.usercenter.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: 许江江
 * @DATE: 2023/2/28
 * @DESCRIPTION: swagger接口文档配置类
 */
@Configuration
@EnableSwagger2
public class UserSwaggerConfig extends BaseSwaggerConfig{
    @Override
    protected SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.action.usercenter.controller")
                .contactEmail("2402208586@qq.com")
                .contactUrl("http://localhost:8081/doc.html")
                .title("用户中心项目")
                .contactName("许江林")
                .build();
    }
}
