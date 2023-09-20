package com.silasonyango.transactionservice.services.rabbitmq.producer;

import com.silasonyango.transactionservice.dtos.rabbitmq.FeeReminderRmqCustomMessage;
import com.silasonyango.transactionservice.dtos.rabbitmq.RabbitMqCustomMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class FeeReminderRabbitMqProducer {
    @Qualifier("customRabbitTemplate")
    @Autowired
    AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    public boolean sendMessage(FeeReminderRmqCustomMessage message) {
        try {
            log.info(String.format("The message to be sent -> %s", message));
            amqpTemplate.convertAndSend(exchange, routingKey, message);
            log.info(String.format("Message with id -> %s was sent successfully", message.getMessageId()));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
