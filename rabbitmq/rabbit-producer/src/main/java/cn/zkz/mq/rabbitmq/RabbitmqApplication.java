package cn.zkz.mq.rabbitmq;

import cn.zkz.mq.rabbitmq.mq.RabbitmqProducer;
import cn.zkz.mq.rabbitmq.mq.RabbitmqSendMsgConfirm;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;

/**
 * 启动类
 */
@SpringBootApplication
public class RabbitmqApplication {

    // 发送消息的模板类
    private static RabbitTemplate rabbitTemplate ;

    public static void main(String[] args) {

        // 启动程序
        ConfigurableApplicationContext applicationContext = SpringApplication.run(RabbitmqApplication.class, args);
        rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);

        // 查看AMQP协议生产者消息流转过程
        // producerMessageTransport() ;

        // 测试FanoutExchange交换机的消息发送
        // fanoutExchangeMessageTransport() ;

        // 测试Direct类型的交换机
        // directExchangeMessageTransport();

        // 测试Topic类型的交换机的消息发送
        // topicExchangeMessageTransport() ;

        // 测试headers交换机的消息发送
        // headersExchangeMessageTransport();

        // 演示消息的过期时间
        // sendMessageTTL() ;
        
        // 演示消息的丢失
        // sendMessageTest() ;

        // 演示事物消息
        // sendTransactionManagerMsg(applicationContext);

        // 演示生产者确认机制消息的发送
        // sendManagerConfirm(applicationContext) ;

        // 不适用生产者确认机制
        sendMessage() ;

    }

    private static void sendMessage() {
        for(int x = 0 ; x < 5 ; x++) {
            rabbitTemplate.convertAndSend("direct.exchange" , "test" , "不适用生产者确认机制 --->" + x) ;
        }
    }

    // 演示生产者确认机制消息的发送
    private static void sendManagerConfirm(ConfigurableApplicationContext applicationContext) {

        RabbitmqSendMsgConfirm sendMsgConfirm = applicationContext.getBean(RabbitmqSendMsgConfirm.class);
        sendMsgConfirm.sendMsg("演示生产者确认机制消息的发送" , "direct.exchange" , "create");

    }

    // 测试事务消息
    private static void sendTransactionManagerMsg(ConfigurableApplicationContext applicationContext) {

        RabbitmqProducer rabbitmqProducer = applicationContext.getBean(RabbitmqProducer.class);
        try {
            rabbitmqProducer.sendMessage();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("事务消息回滚了");
        }

    }

    // 演示消息的丢失
    private static void sendMessageTest() {
        for(int x = 0 ; x < 5 ; x++) {
            rabbitTemplate.convertAndSend("direct.exchange" , "create" , "测试消息的过期时间" , (message)-> {
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
                return message ;
            });
        }
    }

    // 在进行消息发送的时候去设置消息的过期时间
    private static void sendMessageTTL() {

//        rabbitTemplate.convertAndSend("direct.exchange" , "create" , "测试消息的过期时间" , (message) -> {
//            MessageProperties messageProperties = message.getMessageProperties();
//            messageProperties.setExpiration("30000");           // 30秒
//            return message ;
//        });

        rabbitTemplate.convertAndSend("direct.exchange" , "delete" , "测试消息的过期时间");

    }

    // 测试headers交换机的消息发送
    private static void headersExchangeMessageTransport() {
        String message = "headers exchanges message" ;
        MessageProperties messageProperties = new MessageProperties() ;
        messageProperties.setHeader("key1" , "value1");
        messageProperties.setHeader("key2" , "value2");
        Message obj = new Message(message.getBytes() , messageProperties) ;
        rabbitTemplate.convertAndSend("headers.exchange" , "" , obj);
    }

    // 测试Topic类型的交换机的消息发送
    private static void topicExchangeMessageTransport() {

        // routingKey: topic.email.queue  ---> topic.email
        // routingKey: topic.sms.queue  ---> topic.sms
        // routingKey: topic.email.sms  ---> topic.email && topic.sms
        rabbitTemplate.convertAndSend("topic.exchange" , "topic.email.sms" , "topic exchange 测试数据");
    }

    // 测试Direct类型的交换机
    private static void directExchangeMessageTransport() {
        rabbitTemplate.convertAndSend("direct.exchange" , "create" , "direct exchange 测试数据");
    }

    // 测试FanoutExchange交换机的消息发送
    private static void fanoutExchangeMessageTransport() {
        rabbitTemplate.convertAndSend("fanout.exchange" , "itheima" , "fanout exchange 测试数据");
    }

    // 查看AMQP协议生产者消息流转过程测试代码
    private static void producerMessageTransport() {
        rabbitTemplate.convertAndSend("fanout.exchange" , "" , "rabbitmq生产者消息流程过程测试");
    }

}
