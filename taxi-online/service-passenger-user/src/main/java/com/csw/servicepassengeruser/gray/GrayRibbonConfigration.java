package com.csw.servicepassengeruser.gray;

import org.springframework.context.annotation.Bean;

/**
 * TODO (输入本类描述)
 *
 * @author csw
 * @version 1.0
 * @date 2021/1/22 16:39
 */
public class GrayRibbonConfigration {
    @Bean
    public IRule iRule(){
        return new IRule();
    }
}
