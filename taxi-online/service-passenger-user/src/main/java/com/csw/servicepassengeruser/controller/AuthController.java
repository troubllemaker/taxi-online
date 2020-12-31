package com.csw.servicepassengeruser.controller;

import com.csw.internelcommon.dto.ResponseResult;
import com.csw.internelcommon.dto.servicepassengeruser.request.LoginRequest;
import com.csw.servicepassengeruser.service.PassengerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO (输入本类描述)
 *
 * @author csw
 * @version 1.0
 * @date 2020/12/30 15:21
 */
@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private PassengerUserService passengerUserService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody LoginRequest request){

        String passengerPhone = request.getPassengerPhone();
        return passengerUserService.login(passengerPhone);
    }

    /**
     *
     * @param token
     * @return
     * @throws Exception
     */
    @PostMapping("/logout")
    public ResponseResult logout(String token) throws Exception{
        passengerUserService.logout(token);
        return ResponseResult.success("");
    }
}
