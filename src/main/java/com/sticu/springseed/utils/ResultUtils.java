package com.sticu.springseed.utils;

import com.sticu.springseed.common.BaseResponse;
import com.sticu.springseed.common.ErrorCode;

/**
 * 返回工具类
 *
 * @author st
 */
public class ResultUtils {
    private ResultUtils(){}

    /**
     * 成功
     *
     * @param data 响应数据
     * @param <T> 响应数据的数据类型
     * @return 包装响应数据
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok");
    }

    /**
     * 失败
     *
     * @param errorCode 错误码
     * @return 包装响应数据
     */
    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败
     *
     * @param code 错误码
     * @param message 错误信息
     * @return 包装响应数据
     */
    public static BaseResponse error(int code, String message) {
        return new BaseResponse(code, null, message);
    }

    /**
     * 失败
     *
     * @param errorCode 错误码
     * @return 包装响应数据
     */
    public static BaseResponse error(ErrorCode errorCode, String message) {
        return new BaseResponse(errorCode.getCode(), null, message);
    }
}
