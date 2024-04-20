package com.sticu.springseed.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sticu.springseed.common.ErrorCode;
import com.sticu.springseed.model.entity.user.LoginUser;
import com.sticu.springseed.model.entity.user.User;
import com.sticu.springseed.service.UserService;
import com.sticu.springseed.utils.ThrowUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author st
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUserName, username));
        // 校验
        ThrowUtils.throwIf(Objects.isNull(user), ErrorCode.PARAMS_ERROR, "用户名或密码错误");
        //TODO 根据用户查询权限信息 添加到LoginUser中
        List<String> permissions = Arrays.asList("test");

        //封装成UserDetails对象返回 
        return LoginUser.builder()
                .user(user)
                .permissions(permissions)
                .build();
    }
}
