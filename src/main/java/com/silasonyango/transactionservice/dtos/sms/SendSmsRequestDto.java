package com.silasonyango.transactionservice.dtos.sms;

public class SendSmsRequestDto {
    private String recipientPhoneNo;
    private String smsMessage;

    public SendSmsRequestDto() {
    }

    public String getRecipientPhoneNo() {
        return recipientPhoneNo;
    }

    public void setRecipientPhoneNo(String recipientPhoneNo) {
        this.recipientPhoneNo = recipientPhoneNo;
    }

    public String getSmsMessage() {
        return smsMessage;
    }

    public void setSmsMessage(String smsMessage) {
        this.smsMessage = smsMessage;
    }
}
