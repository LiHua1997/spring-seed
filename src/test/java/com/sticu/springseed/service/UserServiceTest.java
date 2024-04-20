package com.sticu.springseed.service;

import com.sticu.springseed.SpringseedTestMockito;
import com.sticu.springseed.mapper.UserMapper;
import com.sticu.springseed.model.dto.user.UserAddRequest;
import com.sticu.springseed.model.entity.user.LoginUser;
import com.sticu.springseed.model.entity.user.User;
import com.sticu.springseed.model.vo.LoginUserVO;
import com.sticu.springseed.service.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("UserServiceImpl 单元测试")
class UserServiceTest extends SpringseedTestMockito {
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private Authentication authentication;
    @Mock
    private UserCache userCache;

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserMapper userMapper;

    @Test
    @DisplayName("添加用户 - 成功")
    void testAddUser_Success() {
        // 模拟数据
        UserAddRequest userAddRequest = new UserAddRequest();
        userAddRequest.setUserName("testUser");
        userAddRequest.setPassword("testPassword");
        userAddRequest.setCheckPassword("testPassword");

        // 模拟数据库操作
        when(userMapper.selectCount(any())).thenReturn(0L);
        // 使用 ArgumentCaptor 捕获传递给 save 方法的参数
        when(userMapper.insert(any())).thenAnswer((Answer<Integer>) invocation -> {
            // 此处模拟mybatis给user注入id
            Object[] args = invocation.getArguments();
            User user = (User) args[0];
            // 设置模拟的用户ID
            user.setSysUserId(12345L);
            // 假设保存成功
            return 1;
        });

        // 调用方法
        long userId = userService.addUser(userAddRequest);

        // 验证
        assertEquals(12345L, userId); // 请根据实际情况修改期望值
        verify(userMapper, times(1)).selectCount(any());
        verify(userMapper, times(1)).insert(any());
    }

    @Test
    @DisplayName("用户登录 - 成功")
    void testLogin_Success() {
        // 模拟数据
        String userAccount = "testUser";
        String userPassword = "testPassword";
        User user = new User();
        user.setSysUserId(1L);
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(user);
        // 模拟认证
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(loginUser);

        // 调用方法
        LoginUserVO loginUserVO = userService.login(userAccount, userPassword);

        // 验证
        assertNotNull(loginUserVO.getToken());
        verify(authenticationManager, times(1)).authenticate(any());
        verify(userCache, times(1)).setLoginUser(any());
    }

    @Test
    @DisplayName("注销用户 - 成功")
    void testLogout_Success() {
        // 模拟登录态
        LoginUser loginUser = new LoginUser();
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(loginUser);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 模拟从缓存中移除用户
        when(userCache.removeLoginUser(any())).thenReturn(true);

        // 调用方法
        Boolean result = userService.logout();

        // 验证
        assertTrue(result); // 验证返回值为true
        verify(userCache, times(1)).removeLoginUser(loginUser); // 验证从缓存中移除用户的方法被调用了一次
    }
}
