package com.silasonyango.transactionservice.dtos.rabbitmq;

import com.silasonyango.transactionservice.utility_classes.Utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FeeReminderRmqCustomMessage {
    String messageId = UUID.randomUUID().toString();
    String messageDate = Utils.convertDateObjectToUserFriendlyDateWithTime(new Date());
    int studentId;

    String admissionNo;
    String studentName;
    int currentTermBalance;
    int currentAnnualBalance;

    public FeeReminderRmqCustomMessage(int studentId, String admissionNo, String studentName, int currentTermBalance, int currentAnnualBalance) {
        this.studentId = studentId;
        this.admissionNo = admissionNo;
        this.studentName = studentName;
        this.currentTermBalance = currentTermBalance;
        this.currentAnnualBalance = currentAnnualBalance;
    }
}
