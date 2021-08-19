package cn.zkz.mq.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Topic类型的交换机消费者代码测试
 */
// @Component
public class RabbitmqHeadersExchangeConsumer {

    // 创建日志记录器
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqHeadersExchangeConsumer.class) ;

    @RabbitListener(queues = "headers.queue_01")
    public void headersExchangeQueue01(String messageBody) {
        LOGGER.info("headers exchange queue 01 message is : {}" , messageBody);
    }

    @RabbitListener(queues = "headers.queue_02")
    public void headersExchangeQueue02(String messageBody) {
        LOGGER.info("headers exchange queue 02 message is : {}" , messageBody);
    }

}
