package cn.zkz.mq.rabbitmq.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 测试Direct类型的交换机
 */
// @Component
public class RabbitmqTopicExchangeConfiguration {

    // 声明交换机
    @Bean(name = "topic.exchange")
    public Exchange topicExchange() {
        return ExchangeBuilder.topicExchange("topic.exchange").durable(true).build() ;
    }

    // 声明队列
    @Bean(name = "topic.email")
    public Queue commonQueue01() {
        return QueueBuilder.durable("topic.email").build() ;
    }

    // 声明队列
    @Bean(name = "topic.sms")
    public Queue commonQueue02() {
        return QueueBuilder.durable("topic.sms").build() ;
    }

    // 完成队列和交换机的绑定
    @Bean
    public Binding topicQueueBindToFanoutQueue01(@Qualifier(value = "topic.exchange") Exchange exchange , @Qualifier(value = "topic.email") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("topic.#.email.#").noargs() ;
    }

    // 完成队列和交换机的绑定
    @Bean
    public Binding topicQueueBindToFanoutQueue02(@Qualifier(value = "topic.exchange") Exchange exchange , @Qualifier(value = "topic.sms") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("topic.#.sms.#").noargs() ;
    }

}
