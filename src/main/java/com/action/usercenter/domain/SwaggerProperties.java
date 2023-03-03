package com.action.usercenter.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @DESCRIPTION: Swagger自定义配置时的属性
 * @author 许江江
 */
@Data
@EqualsAndHashCode
@Builder
public class SwaggerProperties {
    /**
     * API文档扫描的controller路径
     */
    private String apiBasePackage;
    /**
     * 是否要启用登录认证
     */
    private boolean enableSecurity;
    /**
     * 文档标题
     */
    private String title;
    /**
     * 文档联系人姓名
     *
     */
    private String contactName;
    /**
     * 文档联系人网址
     */
    private String contactUrl;
    /**
     * 文档联系人邮箱
     */
    private String contactEmail;

}
