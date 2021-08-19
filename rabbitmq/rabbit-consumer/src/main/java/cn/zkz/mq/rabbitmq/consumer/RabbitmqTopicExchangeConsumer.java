package cn.zkz.mq.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Topic类型的交换机消费者代码测试
 */
// @Component
public class RabbitmqTopicExchangeConsumer {

    // 创建日志记录器
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqTopicExchangeConsumer.class) ;

    @RabbitListener(queues = "topic.email")
    public void topicExchangeQueue01(String messageBody) {
        LOGGER.info("topic exchange email queue message is : {}" , messageBody);
    }

    @RabbitListener(queues = "topic.sms")
    public void topicExchangeQueue02(String messageBody) {
        LOGGER.info("topic exchange sms queue message is : {}" , messageBody);
    }

}
