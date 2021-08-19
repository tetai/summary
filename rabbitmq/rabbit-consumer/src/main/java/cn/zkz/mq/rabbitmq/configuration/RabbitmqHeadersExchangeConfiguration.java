package cn.zkz.mq.rabbitmq.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 测试Direct类型的交换机
 */
// @Component
public class RabbitmqHeadersExchangeConfiguration {

    // 声明交换机
    @Bean(name = "headers.exchange")
    public HeadersExchange headersExchange() {
        return ExchangeBuilder.headersExchange("headers.exchange").durable(true).build() ;
    }

    // 声明队列
    @Bean(name = "headers.queue_01")
    public Queue commonQueue01() {
        return QueueBuilder.durable("headers.queue_01").build() ;
    }

    // 声明队列
    @Bean(name = "headers.queue_02")
    public Queue commonQueue02() {
        return QueueBuilder.durable("headers.queue_02").build() ;
    }

    // 完成队列和交换机的绑定
    @Bean
    public Binding headersQueueBindToFanoutQueue01(@Qualifier(value = "headers.exchange") HeadersExchange exchange , @Qualifier(value = "headers.queue_01") Queue queue) {
        HashMap<String , Object> headersRoutingKeyHashMap = new HashMap<String , Object>() ;
        headersRoutingKeyHashMap.put("key1" , "value1");
        headersRoutingKeyHashMap.put("key2" , "value2");
        return BindingBuilder.bind(queue).to(exchange).whereAny(headersRoutingKeyHashMap).match();
    }

    // 完成队列和交换机的绑定
    @Bean
    public Binding headersQueueBindToFanoutQueue02(@Qualifier(value = "headers.exchange") HeadersExchange exchange , @Qualifier(value = "headers.queue_02") Queue queue) {
        HashMap<String , Object> headersRoutingKeyHashMap = new HashMap<String , Object>() ;
        headersRoutingKeyHashMap.put("key1" , "value1");
        headersRoutingKeyHashMap.put("key2" , "value2");
        return BindingBuilder.bind(queue).to(exchange).whereAll(headersRoutingKeyHashMap).match();
    }

}
