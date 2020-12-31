package com.csw.apipassenger.service.impl;

import com.csw.apipassenger.service.ServiceVerificationCodeRestTemplateService;
import com.csw.internelcommon.dto.ResponseResult;
import com.csw.internelcommon.dto.serviceverificationcode.request.VerifyCodeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
/**
 * TODO (输入本类描述)
 *
 * @author csw
 * @version 1.0
 * @date 2020/12/30 16:39
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ServiceVerificationCodeRestTemplateServiceImpl implements ServiceVerificationCodeRestTemplateService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseResult verifyCode(int passenger, String passengerPhone, String code) {
        String url = "http://service-verifcation-code/verify-code/verify/";

        VerifyCodeRequest request = new VerifyCodeRequest();
        request.setCode(code);
        request.setIdentity(passenger);
        request.setPhoneNumber(passengerPhone);
        ResponseResult result = restTemplate.exchange(url, HttpMethod.POST,new HttpEntity<Object>(request,null),ResponseResult.class).getBody();
        return result;
    }
}
