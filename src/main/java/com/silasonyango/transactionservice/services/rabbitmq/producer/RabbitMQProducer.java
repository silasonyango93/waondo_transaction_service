package com.silasonyango.transactionservice.services.rabbitmq.producer;

import com.silasonyango.transactionservice.dtos.rabbitmq.RabbitMqCustomMessage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class RabbitMQProducer {
    @Qualifier("customRabbitTemplate")
    @Autowired
    AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    public boolean sendMessage(RabbitMqCustomMessage message) {
        try {
            message.setMessageId(UUID.randomUUID().toString());
            message.setMessageDate(new Date());
            amqpTemplate.convertAndSend(exchange, routingKey, message);
            log.info(String.format("Message with id -> %s was sent successfully", message.getMessageId()));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
