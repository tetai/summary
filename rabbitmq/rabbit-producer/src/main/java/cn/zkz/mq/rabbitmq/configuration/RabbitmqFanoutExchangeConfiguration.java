package cn.zkz.mq.rabbitmq.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Rabbitmq和Spring Boot整合的时候的配置类
 */
// @Configuration
public class RabbitmqFanoutExchangeConfiguration {

    // 声明交换机
    @Bean(name = "fanout.exchange")
    public Exchange fanoutExchange() {
        return ExchangeBuilder.fanoutExchange("fanout.exchange").durable(true).build() ;
    }

    // 声明队列
    @Bean(name = "fanout.queue_01")
    public Queue commonQueue01() {
        return QueueBuilder.durable("fanout.queue_01").build() ;
    }

    // 声明队列
    @Bean(name = "fanout.queue_02")
    public Queue commonQueue02() {
        return QueueBuilder.durable("fanout.queue_02").build() ;
    }

    // 完成队列和交换机的绑定
    @Bean
    public Binding fanoutQueueBindToFanoutQueue01(@Qualifier(value = "fanout.exchange") Exchange exchange , @Qualifier(value = "fanout.queue_01") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("").noargs() ;
    }

    // 完成队列和交换机的绑定
    @Bean
    public Binding fanoutQueueBindToFanoutQueue02(@Qualifier(value = "fanout.exchange") Exchange exchange , @Qualifier(value = "fanout.queue_02") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("itcast").noargs() ;
    }

}
