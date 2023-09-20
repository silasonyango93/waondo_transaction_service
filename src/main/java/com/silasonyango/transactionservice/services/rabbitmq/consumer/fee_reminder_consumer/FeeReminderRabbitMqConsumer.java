package com.silasonyango.transactionservice.services.rabbitmq.consumer.fee_reminder_consumer;

import com.silasonyango.transactionservice.dtos.rabbitmq.FeeReminderRmqCustomMessage;
import com.silasonyango.transactionservice.dtos.rabbitmq.RabbitMqCustomMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FeeReminderRabbitMqConsumer {

    @RabbitListener(queues = "fee_payment_reminder_sms_queue")
    public void listener(FeeReminderRmqCustomMessage feeReminderRmqCustomMessage) {
        log.info(String.format("Received message with id -> %s", feeReminderRmqCustomMessage.getMessageId()));
    }
}
