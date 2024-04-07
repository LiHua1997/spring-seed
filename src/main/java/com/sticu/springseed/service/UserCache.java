package com.sticu.springseed.service;

import com.sticu.springseed.model.entity.user.LoginUser;

public interface UserCache {
    void setLoginUser(LoginUser loginUser);

    LoginUser getLoginUser(String userId);

    Boolean removeLoginUser(LoginUser loginUser);
}
