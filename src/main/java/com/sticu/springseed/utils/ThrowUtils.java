package com.sticu.springseed.utils;

import com.sticu.springseed.common.ErrorCode;
import com.sticu.springseed.exception.BusinessException;

/**
 * 抛异常工具类
 *
 * @author st
 */
public class ThrowUtils {
    private ThrowUtils() {}

    /**
     * 条件成立则抛异常
     *
     * @param condition 判断条件
     * @param runtimeException 抛出的异常类
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition 判断条件
     * @param errorCode 错误码
     */
    public static void throwIf(boolean condition, ErrorCode errorCode) {
        throwIf(condition, new BusinessException(errorCode));
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition 判断条件
     * @param errorCode 错误码
     * @param message 错误信息
     */
    public static void throwIf(boolean condition, ErrorCode errorCode, String message) {
        throwIf(condition, new BusinessException(errorCode, message));
    }
}
