package com.csw.apipassenger.service.impl;

import com.csw.apipassenger.service.ServicePassengerUserService;
import com.csw.internelcommon.dto.ResponseResult;
import com.csw.internelcommon.dto.servicepassengeruser.request.LoginRequest;
import com.csw.internelcommon.dto.serviceverificationcode.request.VerifyCodeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * TODO (输入本类描述)
 *
 * @author csw
 * @version 1.0
 * @date 2020/12/30 16:40
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ServicePassengerUserServiceImpl implements ServicePassengerUserService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseResult login(String passengerPhone) {
        String url = "http://service-passenger-user/auth/login";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassengerPhone(passengerPhone);
        ResponseResult result = restTemplate.exchange(url, HttpMethod.POST,new HttpEntity<Object>(loginRequest,null),ResponseResult.class).getBody();

        return result;
    }
}
