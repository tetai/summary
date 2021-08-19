package cn.zkz.mq.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 测试amqp协议的消费者消息流转过程中所涉及到的命令
 */
// @Component
public class RabbitmqConsumer {

    // 创建日志记录器
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqConsumer.class) ;

    @RabbitListener(queues = "fanout.queue")
    public void consumerMessageTransport(String messageBody) {
        LOGGER.info("fanout queue message is : {}" , messageBody);
    }

}
