package com.csw.servicepassengeruser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * TODO (输入本类描述)
 *
 * @author csw
 * @version 1.0
 * @date 2021/1/22 16:51
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("test1")
    public String test1() {
        return restTemplate.getForObject("http://service-sms/test/test1",String.class);
    }
}
