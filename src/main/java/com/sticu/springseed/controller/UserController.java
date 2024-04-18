package com.sticu.springseed.controller;


import com.sticu.springseed.common.BaseResponse;
import com.sticu.springseed.common.ErrorCode;
import com.sticu.springseed.model.dto.user.UserAddRequest;
import com.sticu.springseed.model.dto.user.UserLoginRequest;
import com.sticu.springseed.model.vo.LoginUserVO;
import com.sticu.springseed.service.UserService;
import com.sticu.springseed.utils.ResultUtils;
import com.sticu.springseed.utils.ThrowUtils;
import io.swagger.annotations.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理", description = "用户相关接口")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "用户登录接口")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功", response = BaseResponse.class),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务内部错误"),
    })
    @ApiResponse(code = 200, message = "成功", response = String.class)
    public BaseResponse<LoginUserVO> login(@RequestBody UserLoginRequest userLoginRequest) {
        ThrowUtils.throwIf(userLoginRequest == null, ErrorCode.PARAMS_ERROR);
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        ThrowUtils.throwIf(StringUtils.isAnyBlank(userAccount, userPassword), ErrorCode.PARAMS_ERROR);

        return ResultUtils.success(userService.login(userAccount, userPassword));
    }

    @PostMapping("/logout")
    @ApiOperation(value = "用户登出", notes = "用户登出接口")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功", response = BaseResponse.class),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务内部错误"),
    })
    @ApiResponse(code = 200, message = "成功", response = String.class)
    public BaseResponse<Boolean> logout() {
        return ResultUtils.success(userService.logout());
    }

    @PostMapping
    @ApiOperation(value = "用户新增", notes = "用户新增接口")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功", response = BaseResponse.class),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务内部错误"),
    })
    @ApiResponse(code = 200, message = "成功", response = String.class)
    public BaseResponse<Long> addUser(@RequestBody UserAddRequest userAddRequest) {
        long userId = userService.addUser(userAddRequest);

        return ResultUtils.success(userId);
    }
    @PutMapping
    @ApiOperation("更新用户信息")
    @ApiParam(name = "userId", value = "用户ID", required = true, example = "123")
    @ApiResponse(code = 200, message = "成功", response = String.class)
    public String updateUser(@RequestParam String userId) {
        return "ok";
    }

    @DeleteMapping
    @ApiOperation("删除用户")
    @ApiParam(name = "userId", value = "用户ID", required = true, example = "123")
    @ApiResponse(code = 200, message = "成功", response = String.class)
    public String deleteUser(@RequestParam String userId) {
        return "ok";
    }
}
