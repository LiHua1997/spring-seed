package com.sticu.springseed.common;

import java.io.Serializable;
import lombok.Data;

/**
 * @author st
 */
@Data
public class BaseResponse<T> implements Serializable {

    /**
     * 错误码
     */
    private int code;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 提示消息
     */
    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}
