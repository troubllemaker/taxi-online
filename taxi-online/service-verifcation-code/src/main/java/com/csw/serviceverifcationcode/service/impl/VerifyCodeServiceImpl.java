package com.csw.serviceverifcationcode.service.impl;

import com.csw.internelcommon.constant.CommonStatusEnum;
import com.csw.internelcommon.constant.IdentityConstant;
import com.csw.internelcommon.constant.RedisKeyPrefixConstant;
import com.csw.internelcommon.dto.ResponseResult;
import com.csw.internelcommon.dto.serviceverificationcode.response.VerifyCodeResponse;
import com.csw.serviceverifcationcode.service.VerifyCodeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * TODO (输入本类描述)
 *
 * @author csw
 * @version 1.0
 * @date 2020/12/30 16:56
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class VerifyCodeServiceImpl implements VerifyCodeService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public ResponseResult generate(int identity, String phoneNumber) {
        //校验 发送时限，三挡验证，不能无限制发短信
//        checkSendCodeTimeLimit(phoneNumber);

        // 0.9*9=8.1+1 9,去掉首位为0的情况。 0.11225478552211(0.0-<1)
        String code = String.valueOf((int)((Math.random() * 9 + 1) * Math.pow(10,5)));

        //生成redis key
        String keyPre = generateKeyPreByIdentity(identity);
        String key = keyPre + phoneNumber;
        //存redis，2分钟过期
        BoundValueOperations<String, String> codeRedis = redisTemplate.boundValueOps(key);

        codeRedis.set(code,2, TimeUnit.MINUTES);

        //返回
        VerifyCodeResponse result = new VerifyCodeResponse();
        result.setCode(code);
        return ResponseResult.success(result);
    }

    @Override
    public ResponseResult verify(int identity, String phoneNumber, String code) {
        //生成redis key
        String keyPre = generateKeyPreByIdentity(identity);
        String key = keyPre + phoneNumber;
        BoundValueOperations<String, String> codeRedis = redisTemplate.boundValueOps(key);
        String redisCode = codeRedis.get();

        if(StringUtils.isNotBlank(code)
                && StringUtils.isNotBlank(redisCode)
                && code.trim().equals(redisCode.trim())) {
            return ResponseResult.success("");
        }else {
            return ResponseResult.fail(CommonStatusEnum.VERIFY_CODE_ERROR.getCode(), CommonStatusEnum.VERIFY_CODE_ERROR.getValue());
        }
    }

    private String generateKeyPreByIdentity(int identity) {
        String keyPre = "";
        if (identity == IdentityConstant.PASSENGER){
            keyPre = RedisKeyPrefixConstant.PASSENGER_LOGIN_CODE_KEY_PRE;
        }else if (identity == IdentityConstant.DRIVER){
            keyPre = RedisKeyPrefixConstant.DRIVER_LOGIN_CODE_KEY_PRE;
        }
        return keyPre;
    }
}
