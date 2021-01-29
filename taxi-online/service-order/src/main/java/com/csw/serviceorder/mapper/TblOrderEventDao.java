package com.csw.serviceorder.mapper;

import com.csw.serviceorder.entity.TblOrderEvent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TblOrderEventDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TblOrderEvent record);

    int insertSelective(TblOrderEvent record);

    TblOrderEvent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TblOrderEvent record);

    int updateByPrimaryKey(TblOrderEvent record);

    List<TblOrderEvent> getNeedSendOrders(int i);
}