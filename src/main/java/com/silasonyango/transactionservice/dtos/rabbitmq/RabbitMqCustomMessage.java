package com.silasonyango.transactionservice.dtos.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RabbitMqCustomMessage {
    private String messageId;
    private String message;
    private Date messageDate;
}
