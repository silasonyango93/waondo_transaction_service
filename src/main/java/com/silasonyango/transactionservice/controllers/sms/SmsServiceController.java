package com.silasonyango.transactionservice.controllers.sms;

import com.silasonyango.transactionservice.dtos.rabbitmq.RabbitMqCustomMessage;
import com.silasonyango.transactionservice.dtos.sms.SendSmsRequestDto;
import com.silasonyango.transactionservice.services.rabbitmq.producer.RabbitMQProducer;
import com.silasonyango.transactionservice.services.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SmsServiceController {

    @Autowired
    SmsService smsService;

    @Autowired
    RabbitMQProducer rabbitMQProducer;

    @PostMapping("/send")
    public ResponseEntity<String> sendSms(@RequestBody SendSmsRequestDto sendSmsRequestDto) {
        try {
            rabbitMQProducer.sendMessage(new RabbitMqCustomMessage(
                    null,
                    sendSmsRequestDto.getSmsMessage(),
                    null
            ));
            return new ResponseEntity<String>("Sms sent successfully", HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Problem encountered while sending the sms", HttpStatus.valueOf(500));
        }
    }
}
