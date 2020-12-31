package com.csw.serviceverifcationcode.service;

import com.csw.internelcommon.dto.ResponseResult;

/**
 * TODO (输入本类描述)
 *
 * @author csw
 * @version 1.0
 * @date 2020/12/30 16:55
 */
public interface VerifyCodeService {
    ResponseResult generate(int identity, String phoneNumber);

    ResponseResult verify(int identity, String phoneNumber, String code);
}
