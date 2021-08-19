package cn.zkz.mq.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 测试Fanout交换机的消费者代码
 */
// @Component
public class RabbitmqFanoutExchangeConsumer {

    // 创建日志记录器
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqConsumer.class) ;

    @RabbitListener(queues = "fanout.queue_01")
    public void fanoutExchangeQueue01(String messageBody) {
        LOGGER.info("fanout exchange queue 01 message is : {}" , messageBody);
    }

    @RabbitListener(queues = "fanout.queue_02")
    public void fanoutExchangeQueue02(String messageBody) {
        LOGGER.info("fanout exchange queue 02 message is : {}" , messageBody);
    }

}
