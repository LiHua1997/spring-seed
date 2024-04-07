package com.sticu.springseed.utils;

import com.sticu.springseed.SpringseedTestMockito;
import com.sticu.springseed.common.ErrorCode;
import com.sticu.springseed.exception.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ThrowUtils 单元测试")
class ThrowUtilsTest extends SpringseedTestMockito {

    @Test
    @DisplayName("测试抛出 RuntimeException")
    void testThrowIfWithRuntimeException() {
        assertThrows(RuntimeException.class, () -> {
            ThrowUtils.throwIf(true, new RuntimeException("Test exception"));
        });
    }

    @Test
    @DisplayName("测试抛出 BusinessException - 使用 ErrorCode")
    void testThrowIfWithErrorCode() {
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            ThrowUtils.throwIf(true, ErrorCode.PARAMS_ERROR);
        });

        assertEquals(ErrorCode.PARAMS_ERROR.getCode(), exception.getCode());
    }

    @Test
    @DisplayName("测试抛出 BusinessException - 使用 ErrorCode 和 Message")
    void testThrowIfWithErrorCodeAndMessage() {
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            ThrowUtils.throwIf(true, ErrorCode.SYSTEM_ERROR, "Test message");
        });

        assertEquals(ErrorCode.SYSTEM_ERROR.getCode(), exception.getCode());
        assertEquals("Test message", exception.getMessage());
    }

    @Test
    @DisplayName("测试条件为 false 时不抛出异常")
    void testThrowIfConditionFalse() {
        assertDoesNotThrow(() -> {
            ThrowUtils.throwIf(false, ErrorCode.SYSTEM_ERROR, "Test message");
        });
    }
}
