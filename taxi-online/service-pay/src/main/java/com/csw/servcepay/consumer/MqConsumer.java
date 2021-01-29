package com.csw.servcepay.consumer;

import com.alibaba.fastjson.JSON;
import com.csw.servcepay.entity.TblOrderEvent;
import com.csw.servcepay.mapper.TblOrderEventDao;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * TODO (输入本类描述)
 *
 * @author csw
 * @version 1.0
 * @date 2021/1/27 14:48
 */
@Component
public class MqConsumer {

    @Autowired
    private TblOrderEventDao tblOrderEventDao;

    @Transactional(rollbackFor = Exception.class)
    @JmsListener(destination = "ActiveMQQueue", containerFactory = "jmsListenerContainerFactory")
    public void consumer(TextMessage message, Session session) throws JMSException {
        System.out.println(message.getText());
        TblOrderEvent tblOrderEvent = JSON.parseObject(message.getText(), TblOrderEvent.class);
        try{
            tblOrderEventDao.insert(tblOrderEvent);
            message.acknowledge();
        }catch (Exception e){
            session.recover();
            throw e;
        }
    }


    /**
     * 补偿 处理（人工，脚本）。自己根据自己情况。
     * @param text
     */
    @JmsListener(destination = "DLQ.ActiveMQQueue")
    public void receive2(String text){
        System.out.println("死信队列:"+text);
    }
}
