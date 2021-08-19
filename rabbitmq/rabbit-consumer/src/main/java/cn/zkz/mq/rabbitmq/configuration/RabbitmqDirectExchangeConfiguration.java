package cn.zkz.mq.rabbitmq.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 测试Direct类型的交换机
 */
@Component
public class RabbitmqDirectExchangeConfiguration {

    // 声明死信交换机
    @Bean(name = "dlx.exchange")
    public Exchange dlxExchange() {
        return ExchangeBuilder.directExchange("dlx.exchange").durable(true).build() ;
    }

    // 声明死信队列
    @Bean(name = "dlx.queue")
    public Queue dlxQueue() {
        return QueueBuilder.durable("dlx.queue").build() ;
    }

    // 声明交换机
    @Bean(name = "direct.exchange")
    public Exchange directExchange() {
        return ExchangeBuilder.directExchange("direct.exchange").durable(true).build() ;
    }

    // 声明队列
    @Bean(name = "test_queue")
    public Queue commonQueue03() {
        return QueueBuilder.durable("test_queue").build() ;
    }

    // 声明队列
    @Bean(name = "direct.queue_01")
    public Queue commonQueue01() {
        return QueueBuilder.durable("direct.queue_01").build() ;
    }

    // 声明队列
    @Bean(name = "direct.queue_02")
    public Queue commonQueue02() {
        QueueBuilder queueBuilder = QueueBuilder.durable("direct.queue_02");
        // queueBuilder.lazy() ;
        queueBuilder.deadLetterExchange("dlx.exchange") ;
        queueBuilder.deadLetterRoutingKey("delete") ;
        queueBuilder.ttl(30000) ;
        return queueBuilder.build() ;
    }

    // 完成队列和交换机的绑定
    @Bean
    public Binding directQueueBindToFanoutQueue01(@Qualifier(value = "direct.exchange") Exchange exchange , @Qualifier(value = "direct.queue_01") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("create").noargs() ;
    }

    // 完成队列和交换机的绑定
    @Bean
    public Binding directQueueBindToFanoutQueue02(@Qualifier(value = "direct.exchange") Exchange exchange , @Qualifier(value = "direct.queue_02") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("delete").noargs() ;
    }

    // 完成队列和交换机的绑定
    @Bean
    public Binding directQueueBindToFanoutQueue03(@Qualifier(value = "direct.exchange") Exchange exchange , @Qualifier(value = "test_queue") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("test").noargs() ;
    }

    // 完成死信队列和死信交换机的绑定
    @Bean
    public Binding dlxQueueBindToDlxExchange(@Qualifier(value = "dlx.exchange") Exchange exchange , @Qualifier(value = "dlx.queue") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("delete").noargs() ;
    }

}
