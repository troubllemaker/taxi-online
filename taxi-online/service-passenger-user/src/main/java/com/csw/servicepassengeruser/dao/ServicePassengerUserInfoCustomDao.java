package com.csw.servicepassengeruser.dao;

import com.csw.servicepassengeruser.entity.ServicePassengerUserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO (输入本类描述)
 *
 * @author csw
 * @version 1.0
 * @date 2020/12/30 16:02
 */
@Mapper
public interface ServicePassengerUserInfoCustomDao extends ServicePassengerUserInfoDao{
    /**
     * 根据手机号查询乘客信息
     * @param passengerPhone
     * @return
     */
    ServicePassengerUserInfo selectByPhoneNumber(String passengerPhone);
}
