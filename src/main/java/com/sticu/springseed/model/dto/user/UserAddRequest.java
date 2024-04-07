package com.sticu.springseed.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "UserAddRequest", description = "用户新增请求")
@Data
public class UserAddRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名", example = "john_doe")
    private String userName;

    @ApiModelProperty(value = "昵称", example = "John Doe")
    private String nickName;

    @ApiModelProperty(value = "密码", example = "password123")
    private String password;

    @ApiModelProperty(value = "确认密码", example = "password123")
    private String checkPassword;

    @ApiModelProperty(value = "账号状态（0正常 1停用）", example = "0")
    private String status;

    @ApiModelProperty(value = "邮箱", example = "john.doe@example.com")
    private String email;

    @ApiModelProperty(value = "手机号", example = "1234567890")
    private String phoneNumber;

    @ApiModelProperty(value = "用户性别（0男，1女，2未知）", example = "0")
    private String gender;

    @ApiModelProperty(value = "头像", example = "https://example.com/avatar.jpg")
    private String avatar;

    @ApiModelProperty(value = "用户类型（0管理员，1普通用户）", example = "1")
    private String userType;
}
