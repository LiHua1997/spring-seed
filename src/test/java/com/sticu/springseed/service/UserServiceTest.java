package com.sticu.springseed.service;

import com.sticu.springseed.SpringseedTestMockito;
import com.sticu.springseed.model.dto.user.UserAddRequest;
import com.sticu.springseed.model.vo.LoginUserVO;
import com.sticu.springseed.service.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("UserServiceImpl 单元测试")
class UserServiceTest extends SpringseedTestMockito {
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserCache userCache;

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("添加用户 - 成功")
    void testAddUser_Success() {
        // 模拟数据
        UserAddRequest userAddRequest = new UserAddRequest();
        userAddRequest.setUserName("testUser");
        userAddRequest.setPassword("testPassword");
        userAddRequest.setCheckPassword("testPassword");

        // 模拟数据库操作
        when(userService.getBaseMapper().selectCount(any())).thenReturn(0L);
        when(userService.save(any())).thenReturn(true);

        // 调用方法
        long userId = userService.addUser(userAddRequest);

        // 验证
        assertEquals(0L, userId); // 请根据实际情况修改期望值
        verify(userService.getBaseMapper(), times(1)).selectCount(any());
        verify(userService, times(1)).save(any());
    }

    @Test
    @DisplayName("用户登录 - 成功")
    void testLogin_Success() {
        // 模拟数据
        String userAccount = "testUser";
        String userPassword = "testPassword";

        // 模拟认证
        when(authenticationManager.authenticate(any())).thenReturn(mock(Authentication.class));

        // 调用方法
        LoginUserVO loginUserVO = userService.login(userAccount, userPassword);

        // 验证
        assertEquals("your_expected_token", loginUserVO.getToken());
        verify(authenticationManager, times(1)).authenticate(any());
        verify(userCache, times(1)).setLoginUser(any());
    }
}
