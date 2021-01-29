package com.csw.serviceorder.schedule;

import com.alibaba.fastjson.JSON;
import com.csw.serviceorder.entity.TblOrderEvent;
import com.csw.serviceorder.mapper.TblOrderEventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO (输入本类描述)
 *
 * @author csw
 * @version 1.0
 * @date 2021/1/27 14:30
 */
@Component
public class MqTask {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private TblOrderEventDao tblOrderEventDao;
    @Transactional(rollbackFor = Exception.class)
    @Scheduled(fixedRate = 5000)
    public void add() {
        System.out.println("定时任务开始执行了~~");
        List<TblOrderEvent> needSendOrders = tblOrderEventDao.getNeedSendOrders(1);
        for (TblOrderEvent tblorderEvent:needSendOrders) {
            tblorderEvent.setOrderType("2");
            tblOrderEventDao.updateByPrimaryKey(tblorderEvent);
            jmsTemplate.convertAndSend("ActiveMQQueue", JSON.toJSONString(tblorderEvent));
        }
    }
}
