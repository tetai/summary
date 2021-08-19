package cn.zkz.mq.rabbitmq.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Direct类型的交换机的消费者
 */
@Component
public class RabbitmqDirectExchangeConsumer {

    // 创建日志记录器
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqDirectExchangeConsumer.class) ;

    @RabbitListener(queues = "direct.queue_01")
    public void directExchangeQueue01(String messageBody , Channel channel , Message message) {

        try {
            LOGGER.info("direct exchange queue 01 message is : {}" , messageBody);

            // 手动应答
            channel.basicAck(message.getMessageProperties().getDeliveryTag() , false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RabbitListener(queues = "direct.queue_02")
    public void directExchangeQueue02(String messageBody) {
        LOGGER.info("direct exchange queue 02 message is : {}" , messageBody);
    }


    @RabbitListener(queues = "test_queue")
    public void directExchangeQueue03(String messageBody , Channel channel , Message message) {

        try {
            LOGGER.info("direct exchange test_queue message is : {}" , messageBody);

            // 手动应答
            channel.basicAck(message.getMessageProperties().getDeliveryTag() , false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
