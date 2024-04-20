package com.sticu.springseed.service;

import com.sticu.springseed.SpringseedTestMockito;
import com.sticu.springseed.model.entity.user.LoginUser;
import com.sticu.springseed.model.entity.user.User;
import com.sticu.springseed.service.impl.UserCacheImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("UserCacheImpl 单元测试")
class UserCacheImplTest extends SpringseedTestMockito {

    @Mock
    private RedisTemplate<String, LoginUser> redisTemplate;
    @Mock
    private ValueOperations<String, LoginUser> valueOps;

    @InjectMocks
    private UserCacheImpl userCache;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(userCache, "valueOps", valueOps);
    }
    @Test
    @DisplayName("测试设置登录用户")
    void testSetLoginUser() {
        LoginUser loginUser = new LoginUser();
        User user = new User();
        user.setUserName("tom");
        user.setSysUserId(3L);
        loginUser.setUser(user);
        String key = "login:" + loginUser.getUser().getSysUserId().toString();

        userCache.setLoginUser(loginUser);

        verify(valueOps, times(1)).set(key, loginUser, 3600, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("测试获取登录用户")
    void testGetLoginUser() {
        LoginUser loginUser = new LoginUser();
        User user = new User();
        user.setUserName("tom");
        user.setSysUserId(1L);
        loginUser.setUser(user);
        String key = "login:" + loginUser.getUser().getSysUserId().toString();

        when(valueOps.get(key)).thenReturn(loginUser);

        LoginUser result = userCache.getLoginUser(loginUser.getUser().getSysUserId().toString());

        verify(valueOps, times(1)).get(key);

        assertNotNull(result);
        assertEquals(loginUser, result);
    }

    @Test
    @DisplayName("测试移除登录用户")
    void testRemoveLoginUser() {
        LoginUser loginUser = new LoginUser();
        User user = new User();
        user.setUserName("tom");
        user.setSysUserId(3L);
        loginUser.setUser(user);
        String key = "login:" + loginUser.getUser().getSysUserId().toString();

        when(redisTemplate.delete(key)).thenReturn(true);

        Boolean result = userCache.removeLoginUser(loginUser);

        verify(redisTemplate, times(1)).delete(key);

        assertTrue(result);
    }
}
