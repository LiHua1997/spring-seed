package com.sticu.springseed.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
@ApiModel(value = "LoginUserVO", description = "用户登录视图")
@Data
@Builder
public class LoginUserVO {
    @ApiModelProperty(example = "xxxxxx.xxxxxxx.xxxx", value = "Authorization Token")
    private String token;
}
