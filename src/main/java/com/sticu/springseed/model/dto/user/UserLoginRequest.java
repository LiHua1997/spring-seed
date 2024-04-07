package com.sticu.springseed.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "UserLoginRequest", description = "用户登录请求")
@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户账号", required = true, example = "test")
    private String userAccount;
    @ApiModelProperty(value = "用户密码", required = true, example = "12345678")
    private String userPassword;

}
