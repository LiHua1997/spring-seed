package com.sticu.springseed.service;

import com.sticu.springseed.SpringseedTestMockito;
import com.sticu.springseed.exception.BusinessException;
import com.sticu.springseed.model.entity.user.LoginUser;
import com.sticu.springseed.model.entity.user.User;
import com.sticu.springseed.service.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("UserDetailsServiceImpl 单元测试")
class UserDetailsServiceImplTest extends SpringseedTestMockito {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    @DisplayName("加载存在的用户")
    void loadUserByUsername_ExistingUser() {
        String username = "testUser";
        User mockUser = new User();
        mockUser.setUserName(username);
        when(userService.getOne(any()))
                .thenReturn(mockUser);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        assertNotNull(userDetails);
        assertTrue(userDetails instanceof LoginUser);
        assertEquals(username, userDetails.getUsername());

        verify(userService, times(1)).getOne(any());
    }

    @Test
    @DisplayName("加载不存在的用户")
    void loadUserByUsername_NonExistingUser() {
        String username = "nonExistingUser";
        when(userService.getOne(any()))
                .thenReturn(null);

        BusinessException exception = assertThrows(BusinessException.class,
                () -> userDetailsService.loadUserByUsername(username));

        assertEquals("用户名或密码错误", exception.getMessage());

        verify(userService, times(1)).getOne(any());
    }
}