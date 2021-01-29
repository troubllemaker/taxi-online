package com.csw.servcepay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class ServcePayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServcePayApplication.class, args);
    }

}
