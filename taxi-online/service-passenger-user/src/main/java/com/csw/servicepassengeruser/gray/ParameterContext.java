package com.csw.servicepassengeruser.gray;

import org.springframework.stereotype.Component;

/**
 * TODO (输入本类描述)
 *
 * @author csw
 * @version 1.0
 * @date 2021/1/22 15:30
 */
@Component
public class ParameterContext {
    private static ThreadLocal parameterContext = new ThreadLocal() ;

    public static<T> void set(T t) {
        parameterContext.set(t);
    }

    public static <T> T get() {
        return (T) parameterContext.get();
    }
}
