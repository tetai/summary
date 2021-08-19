package cn.zkz.mq.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

// @Component
public class RabbitmqDlxQueueConsumer {

    // 创建日志记录器
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqDlxQueueConsumer.class) ;

    @RabbitListener(queues = "dlx.queue")
    public void dlxQueueConsumer(String msg) {
        LOGGER.info("dlx queue msg is : {} " , msg );
    }

}