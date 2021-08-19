package cn.zkz.mq.rabbitmq.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RabbitmqProducer {

    // 定义日志记录器
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqProducer.class) ;

    @Autowired
    private RabbitTemplate rabbitTemplate ;

    @Transactional(rollbackFor = Exception.class , transactionManager = "rabbitTransactionManager")
    public void sendMessage() {


        rabbitTemplate.setChannelTransacted(true);      // 将消息通道设置为事务机制
        for(int x = 0 ; x < 5 ; x++) {

            String msg = "测试生产者事务消息 ---> " + x ;
            rabbitTemplate.convertAndSend("direct.exchange" , "create" , msg.getBytes());
            // int a = 1 / 0 ;
            LOGGER.info("transactionManager message send success ----> " + msg);

        }


    }

}