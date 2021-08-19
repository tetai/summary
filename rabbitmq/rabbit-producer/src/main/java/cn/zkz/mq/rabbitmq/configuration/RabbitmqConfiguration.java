package cn.zkz.mq.rabbitmq.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Rabbitmq和Spring Boot整合的时候的配置类
 */
// @Configuration
public class RabbitmqConfiguration {

    // 声明交换机
    @Bean(name = "fanout.exchange")
    public Exchange fanoutExchange() {
        return ExchangeBuilder.fanoutExchange("fanout.exchange").durable(true).build() ;
    }

    // 声明队列
    @Bean(name = "fanout.queue")
    public Queue commonQueue() {
        return QueueBuilder.durable("fanout.queue").build() ;
    }

    // 完成队列和交换机的绑定
    @Bean
    public Binding fanoutQueueBindToFanoutQueue(@Qualifier(value = "fanout.exchange") Exchange exchange , @Qualifier(value = "fanout.queue") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("").noargs() ;
    }

}
