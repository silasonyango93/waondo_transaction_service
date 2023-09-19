package com.silasonyango.transactionservice.services.sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    public boolean sendSms(String recipientPhoneNo, String textMessage) {
        try {
            Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"), System.getenv("TWILIO_AUTH_TOKEN"));
            Message.creator(new PhoneNumber(recipientPhoneNo),
                    new PhoneNumber(System.getenv("TWILIO_PHONE_NUMBER")), textMessage).create();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
