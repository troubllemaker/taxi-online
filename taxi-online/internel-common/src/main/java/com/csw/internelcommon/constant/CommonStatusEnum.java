package com.csw.internelcommon.constant;

import lombok.Getter;

/**
 * TODO (输入本类描述)
 *
 * @author csw
 * @version 1.0
 * @date 2020/12/30 15:47
 */
public enum CommonStatusEnum {

    /**短信验证码服务	10001-10099*/
    VERIFY_CODE_ERROR(10001,"短信验证码验证失败"),

    /**
     * 	操作异常
     */
    INTERNAL_SERVER_EXCEPTION(-1, "exception"),
    /**
     * 	操作成功
     */
    SUCCESS(0, "success");
    @Getter
    private final  int code;
    @Getter
    private final String value;


    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
