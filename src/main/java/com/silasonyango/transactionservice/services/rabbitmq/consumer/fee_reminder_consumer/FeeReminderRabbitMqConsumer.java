package com.silasonyango.transactionservice.services.rabbitmq.consumer.fee_reminder_consumer;

import com.silasonyango.transactionservice.dtos.rabbitmq.FeeReminderRmqCustomMessage;
import com.silasonyango.transactionservice.services.sms.SmsService;
import com.silasonyango.transactionservice.utility_classes.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class FeeReminderRabbitMqConsumer {

    @Autowired
    SmsService smsService;

    @RabbitListener(queues = "fee_payment_reminder_sms_queue")
    public void listener(FeeReminderRmqCustomMessage feeReminderRmqCustomMessage) {
        log.info(String.format("Received message with id -> %s", feeReminderRmqCustomMessage.getMessageId()));
        if (feeReminderRmqCustomMessage.getParentPhoneNumber() != null) {
            String textMessage = String.format("FROM WAONDO SEC SCH.\nGreetings parent/guardian.\n%s's current term fee balance " +
                            " is KES %s as at %s. Kindly pay by %s to avoid any inconveniences.\nThe Principal."
                    , feeReminderRmqCustomMessage.getStudentName()
                    , Utils.formatIntegerToCommaSeperatedValue(feeReminderRmqCustomMessage.getCurrentTermBalance())
                    , Utils.convertDateObjectToUserFriendlyDateWithTime(new Date())
                    , Utils.convertToUserFriendlyDate(feeReminderRmqCustomMessage.getPaymentDeadlineDate(), "yyyy-MM-dd"));
            smsService.sendSms(
                    feeReminderRmqCustomMessage.getParentPhoneNumber(),
                    textMessage
            );
        }
    }
}
