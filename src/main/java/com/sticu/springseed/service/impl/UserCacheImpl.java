package com.sticu.springseed.service.impl;

import com.sticu.springseed.model.entity.user.LoginUser;
import com.sticu.springseed.service.UserCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserCacheImpl implements UserCache {
    private static final String KEY_PREFIX = "login:";
    private static final int LOGIN_TIMEOUT_SECONDS = 60 * 60;
    private final RedisTemplate<String, LoginUser> redisTemplate;
    private final ValueOperations<String, LoginUser> valueOps;

    @Autowired
    public UserCacheImpl(RedisTemplate<String, LoginUser> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueOps = redisTemplate.opsForValue();
    }

    @Override
    public void setLoginUser(LoginUser loginUser) {
        String key = generateKey(loginUser.getUser().getSysUserId().toString());
        valueOps.set(key, loginUser, LOGIN_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    @Override
    public LoginUser getLoginUser(String userId) {
        String key = generateKey(userId);
        return valueOps.get(key);
    }

    @Override
    public Boolean removeLoginUser(LoginUser loginUser) {
        String key = generateKey(loginUser.getUser().getSysUserId().toString());
        return redisTemplate.delete(key);
    }

    private String generateKey(String userId) {
        return KEY_PREFIX + userId;
    }
}
