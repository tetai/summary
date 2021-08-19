package cn.zkz.mq.rabbitmq.mq;

import com.rabbitmq.client.Return;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqSendMsgConfirm implements RabbitTemplate.ConfirmCallback , RabbitTemplate.ReturnCallback {

    // 定义日志记录对象
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqSendMsgConfirm.class) ;

    @Autowired
    private RabbitTemplate rabbitTemplate ;

    public void sendMsg(String msg , String exchangeName , String routingKey) {

        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
        CorrelationData correlationData = new CorrelationData(msg) ;
        rabbitTemplate.convertAndSend(exchangeName , routingKey , msg , message -> {
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);         // 进行消息持久化操作
            return message ;
        } , correlationData) ;


    }

    // 服务端给生产端进行应答以后所需要执行的回调函数
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack) {
            LOGGER.info("消息发送成功");
        }else {
            LOGGER.info("消息发送失败");
        }
    }

    // 在进行return命令处理的时候多对应的回调函数
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        LOGGER.info("消息没有被投递到指定的队列 ---> " + new String(message.getBody()));
    }
}
