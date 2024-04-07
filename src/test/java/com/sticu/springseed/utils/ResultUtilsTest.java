package com.sticu.springseed.utils;

import com.sticu.springseed.SpringseedTestMockito;
import com.sticu.springseed.common.BaseResponse;
import com.sticu.springseed.common.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ResultUtils 单元测试")
class ResultUtilsTest extends SpringseedTestMockito {

    @Test
    @DisplayName("测试成功响应生成")
    void success() {
        String testData = "Test Data";
        BaseResponse<String> response = ResultUtils.success(testData);

        assertNotNull(response);
        assertEquals(0, response.getCode());
        assertEquals(testData, response.getData());
        assertEquals("ok", response.getMessage());
    }

    @Test
    @DisplayName("测试错误响应生成 - 使用 ErrorCode")
    void errorWithErrorCode() {
        ErrorCode errorCode = ErrorCode.PARAMS_ERROR;
        BaseResponse response = ResultUtils.error(errorCode);

        assertNotNull(response);
        assertEquals(errorCode.getCode(), response.getCode());
        assertNull(response.getData());
        assertEquals(errorCode.getMessage(), response.getMessage());
    }

    @Test
    @DisplayName("测试错误响应生成 - 使用 Code 和 Message")
    void errorWithCodeAndMessage() {
        int code = 500;
        String message = "Internal Server Error";
        BaseResponse response = ResultUtils.error(code, message);

        assertNotNull(response);
        assertEquals(code, response.getCode());
        assertNull(response.getData());
        assertEquals(message, response.getMessage());
    }

    @Test
    @DisplayName("测试错误响应生成 - 使用 ErrorCode 和 Message")
    void errorWithErrorCodeAndMessage() {
        ErrorCode errorCode = ErrorCode.PARAMS_ERROR;
        String message = "Resource Not Found";
        BaseResponse response = ResultUtils.error(errorCode, message);

        assertNotNull(response);
        assertEquals(errorCode.getCode(), response.getCode());
        assertNull(response.getData());
        assertEquals(message, response.getMessage());
    }
}
