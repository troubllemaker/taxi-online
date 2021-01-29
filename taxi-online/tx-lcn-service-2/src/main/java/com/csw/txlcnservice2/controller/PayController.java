package com.csw.txlcnservice2.controller;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.csw.txlcnservice2.entity.TblOrder;
import com.csw.txlcnservice2.mapper.TblOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController {


    @Autowired
    private TblOrderDao tblPayDao;

    @PostMapping("/add-pay")
    @Transactional(rollbackFor = Exception.class)
    @LcnTransaction
    public String addPay(@RequestBody TblOrder bean){
        tblPayDao.insert(bean);
        return "新增支付成功";

    }
}
