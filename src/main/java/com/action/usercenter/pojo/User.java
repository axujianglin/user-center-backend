package com.action.usercenter.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 许江林
 * @since 2023-02-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户昵称")
    @TableField
    private String userName;

    @ApiModelProperty(value = "登录账号")
    private String userAccount;

    @ApiModelProperty(value = "用户头像")
    @TableField("avatarUrl")
    private String avatarUrl;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "用户电话")
    private String phone;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "用户状态，0表示正常，1表示不可用")
    private Integer status;

    @ApiModelProperty(value = "用户角色，0表示普通用户，1表示管理员")
    private Integer userRole;

    @ApiModelProperty(value = "用户唯一的星球编号")
    private String planetNumber;

    @ApiModelProperty(value = "创建时间")
    @TableField
    private Date creatTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否删除，0表示未删除，1表示删除")
    @TableLogic
    private Integer isDeleted;


}
