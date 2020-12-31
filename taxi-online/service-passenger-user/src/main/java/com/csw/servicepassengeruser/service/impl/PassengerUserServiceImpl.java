package com.csw.servicepassengeruser.service.impl;

import com.csw.internelcommon.constant.RedisKeyPrefixConstant;
import com.csw.internelcommon.dto.ResponseResult;
import com.csw.internelcommon.util.JwtUtil;
import com.csw.servicepassengeruser.dao.ServicePassengerUserInfoCustomDao;
import com.csw.servicepassengeruser.entity.ServicePassengerUserInfo;
import com.csw.servicepassengeruser.service.PassengerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * TODO (输入本类描述)
 *
 * @author csw
 * @version 1.0
 * @date 2020/12/30 15:36
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PassengerUserServiceImpl implements PassengerUserService {
    @Autowired
    private ServicePassengerUserInfoCustomDao servicePassengerUserInfoCustomDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public ResponseResult login(String passengerPhone) {
        ServicePassengerUserInfo passengerUserInfo = new ServicePassengerUserInfo();
        passengerUserInfo.setCreateTime(new Date());
        passengerUserInfo.setPassengerGender((byte)1);
        passengerUserInfo.setPassengerName("");
        passengerUserInfo.setRegisterDate(new Date());
        passengerUserInfo.setUserState((byte)1);
        passengerUserInfo.setPassengerPhone(passengerPhone);

        servicePassengerUserInfoCustomDao.insertSelective(passengerUserInfo);
        Long id = passengerUserInfo.getId();
        String token = JwtUtil.createToken(id + "", new Date());
        // 存入redis，设置过期时间。
        BoundValueOperations<String, String> stringStringBoundValueOperations = redisTemplate.boundValueOps(RedisKeyPrefixConstant.PASSENGER_LOGIN_TOKEN_APP_KEY_PRE + id);
        stringStringBoundValueOperations.set(token,30, TimeUnit.DAYS);
        return ResponseResult.success(token);
    }

    @Override
    public void logout(String token) {

    }

}
