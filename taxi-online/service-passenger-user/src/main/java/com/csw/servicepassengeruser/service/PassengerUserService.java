package com.csw.servicepassengeruser.service;

import com.csw.internelcommon.dto.ResponseResult;

/**
 * TODO (输入本类描述)
 *
 * @author csw
 * @version 1.0
 * @date 2020/12/30 15:35
 */
public interface PassengerUserService {
    ResponseResult login(String passengerPhone);

    void logout(String token);
}
