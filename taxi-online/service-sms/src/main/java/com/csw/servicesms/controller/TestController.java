package com.csw.servicesms.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Value("${server.port}")
    public String port;

    @RequestMapping("test1")
    public String test1() {
        return "test1" + port;
    }
}
