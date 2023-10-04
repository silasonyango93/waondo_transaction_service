package com.silasonyango.transactionservice.controllers.communication;

import com.silasonyango.transactionservice.dtos.rabbitmq.FeeReminderRmqCustomMessage;
import com.silasonyango.transactionservice.dtos.sms.SendSmsAnnouncementRequestDto;
import com.silasonyango.transactionservice.services.communication.sms.ParentGeneralCommunicationSmsService;
import com.silasonyango.transactionservice.services.rabbitmq.producer.FeeReminderRabbitMqProducer;
import com.silasonyango.transactionservice.services.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/announcements")
public class GeneralAnnouncementsSmsServiceController {

    @Autowired
    SmsService smsService;

    @Autowired
    ParentGeneralCommunicationSmsService parentGeneralCommunicationSmsService;

    @PostMapping("/broadcast/send-to-all-students-not-completed-school")
    public ResponseEntity<String> sendBroadcastSmsToAllStudentsNotCompletedSchool(
            @RequestBody SendSmsAnnouncementRequestDto sendSmsAnnouncementRequestDto) {
        if (parentGeneralCommunicationSmsService.sendGeneralCommunicationToParentsOfAllStudentsNotCompletedSchool(
                sendSmsAnnouncementRequestDto.getAnnouncementMessage()
        )) {
            return new ResponseEntity<String>("Sms broadcast sent successfully", HttpStatus.valueOf(200));
        } else {
            return new ResponseEntity<String>("Problem encountered while sending the broadcast", HttpStatus.valueOf(500));
        }
    }

    @PostMapping("/broadcast/send-to-parents-of-particular-lot")
    public ResponseEntity<String> sendBroadcastSmsToParentsOfParticularLot(
            @RequestBody SendSmsAnnouncementRequestDto sendSmsAnnouncementRequestDto) {
        if (parentGeneralCommunicationSmsService.sendGeneralCommunicationToParentsOfAParticularLot(
                sendSmsAnnouncementRequestDto.getAnnouncementMessage(),
                sendSmsAnnouncementRequestDto.getReferenceId()
        )) {
            return new ResponseEntity<String>("Sms broadcast sent successfully", HttpStatus.valueOf(200));
        } else {
            return new ResponseEntity<String>("Problem encountered while sending the broadcast", HttpStatus.valueOf(500));
        }
    }

    @PostMapping("/broadcast/send-to-parents-of-particular-class-stream")
    public ResponseEntity<String> sendBroadcastSmsToParentsOfParticularClassStream(
            @RequestBody SendSmsAnnouncementRequestDto sendSmsAnnouncementRequestDto) {

        if (parentGeneralCommunicationSmsService.sendGeneralCommunicationToParentsOfAParticularClassStream(
                sendSmsAnnouncementRequestDto.getAnnouncementMessage(),
                sendSmsAnnouncementRequestDto.getReferenceId()
        )) {
            return new ResponseEntity<String>("Sms broadcast sent successfully", HttpStatus.valueOf(200));
        } else {
            return new ResponseEntity<String>("Problem encountered while sending the broadcast", HttpStatus.valueOf(500));
        }
    }

    @PostMapping("/broadcast/send-to-specific-list-of-students")
    public ResponseEntity<String> sendToSpecificListOfStudents(
            @RequestBody SendSmsAnnouncementRequestDto sendSmsAnnouncementRequestDto) {

        if (parentGeneralCommunicationSmsService.sendCommunicationToParentsOfSpecificListOfStudents(
                sendSmsAnnouncementRequestDto.getAnnouncementMessage(),
                sendSmsAnnouncementRequestDto.getReferenceList()
        )) {
            return new ResponseEntity<String>("Sms broadcast sent successfully", HttpStatus.valueOf(200));
        } else {
            return new ResponseEntity<String>("Problem encountered while sending the broadcast", HttpStatus.valueOf(500));
        }
    }
}
