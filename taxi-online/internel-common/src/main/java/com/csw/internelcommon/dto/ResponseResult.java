package com.csw.internelcommon.dto;

import com.csw.internelcommon.constant.CommonStatusEnum;
import lombok.experimental.Accessors;

/**
 * TODO (输入本类描述)
 *
 * @author csw
 * @version 1.0
 * @date 2020/12/30 15:23
 */

public class ResponseResult<T> {
    /**
     * 响应码
     */
    private int code;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;
    /**
     * 返回成功数据（status：1）
     *
     * @param data 数据内容
     * @param <T>  数据类型
     * @return ResponseResult实例
     */
    public static <T> ResponseResult success(T data) {
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue()).setData(data);
    }

    public static <T> ResponseResult fail(String s) {
        return new ResponseResult().setCode(CommonStatusEnum.INTERNAL_SERVER_EXCEPTION.getCode()).setMessage(CommonStatusEnum.INTERNAL_SERVER_EXCEPTION.getValue()).setData(s);

    }

    public static ResponseResult fail(int code, String value) {
        return new ResponseResult().setCode(code).setMessage(value);
    }


    public int getCode() {
        return code;
    }

    public ResponseResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseResult setData(T data) {
        this.data = data;
        return this;
    }
}
