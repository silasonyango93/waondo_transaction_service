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
    private String messageId = UUID.randomUUID().toString();
    private String messageDate = Utils.convertDateObjectToUserFriendlyDateWithTime(new Date());
    private int studentId;
    private String admissionNo;
    private String studentName;
    private int currentTermBalance;
    private int currentAnnualBalance;
    private String parentPhoneNumber;
    private String paymentDeadlineDate;

    public FeeReminderRmqCustomMessage(int studentId, String admissionNo, String studentName, int currentTermBalance
            , int currentAnnualBalance, String parentPhoneNumber, String paymentDeadlineDate) {
        this.studentId = studentId;
        this.admissionNo = admissionNo;
        this.studentName = studentName;
        this.currentTermBalance = currentTermBalance;
        this.currentAnnualBalance = currentAnnualBalance;
        this.parentPhoneNumber = parentPhoneNumber;
        this.paymentDeadlineDate = paymentDeadlineDate;
    }
}
