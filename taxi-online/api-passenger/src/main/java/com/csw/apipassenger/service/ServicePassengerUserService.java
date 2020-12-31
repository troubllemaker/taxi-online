package com.csw.apipassenger.service;

import com.csw.internelcommon.dto.ResponseResult;

/**
 * TODO (输入本类描述)
 *
 * @author csw
 * @version 1.0
 * @date 2020/12/30 16:37
 */
public interface ServicePassengerUserService {
    ResponseResult login(String passengerPhone);
}
