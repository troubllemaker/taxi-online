package com.csw.servicepassengeruser.gray;

import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * TODO (输入本类描述)
 *
 * @author csw
 * @version 1.0
 * @date 2021/1/22 16:17
 */
@Aspect
@Component
public class IRueAop {
    @Pointcut("execution(* com.csw.servicepassengeruser.controller..*Controller*.*(..))")
    private void anyRequest(){
    }
    @Before(value = "anyRequest()")
    public void before(JoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String version = request.getHeader("version");
        if("v1".equalsIgnoreCase(version)){
            RibbonFilterContextHolder.getCurrentContext().add("version","v1");
        }
        /*Map map = new HashMap<>(2);
        map.put("version", version);
        ParameterContext.set(map);*/

    }
}
