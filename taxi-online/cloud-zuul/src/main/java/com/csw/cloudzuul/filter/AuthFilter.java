package com.csw.cloudzuul.filter;

import com.csw.internelcommon.constant.RedisKeyPrefixConstant;
import com.csw.internelcommon.util.JwtInfo;
import com.csw.internelcommon.util.JwtUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * TODO (输入本类描述)
 *
 * @author csw
 * @version 1.0
 * @date 2020/12/31 16:34
 */
@Component
public class AuthFilter extends ZuulFilter {

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        System.out.println("shouldFilter");
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String authrization = request.getHeader("authrization");
        if(!StringUtils.isEmpty(authrization)){
            JwtInfo jwtInfo = JwtUtil.parseToken(authrization);
            if(jwtInfo != null){
                String tokenUserId = jwtInfo.getSubject();
                BoundValueOperations<String, String> stringStringBoundValueOperations = redisTemplate.boundValueOps(RedisKeyPrefixConstant.PASSENGER_LOGIN_TOKEN_APP_KEY_PRE + tokenUserId);
                String s = stringStringBoundValueOperations.get();
                if(StringUtils.isEmpty(s)){
                    requestContext.set("userId", tokenUserId);
                    requestContext.setSendZuulResponse(false);
                    requestContext.setResponseStatusCode(HttpStatus.SC_OK);
                    requestContext.setResponseBody("auth success");
                    return null;
                }
            }
        }
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
        requestContext.setResponseBody("auth fail");
        return null;
    }
}
