package com.silasonyango.transactionservice.services.communication.sms;

import com.silasonyango.transactionservice.dtos.rabbitmq.FeeReminderRmqCustomMessage;
import com.silasonyango.transactionservice.dtos.rabbitmq.RabbitMqMessageDistinctionEnum;
import com.silasonyango.transactionservice.services.rabbitmq.producer.FeeReminderRabbitMqProducer;
import com.silasonyango.transactionservice.services.sms.SmsService;
import com.silasonyango.transactionservice.services.student.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ParentGeneralCommunicationSmsService {

    @Autowired
    StudentsService studentsService;

    @Autowired
    FeeReminderRabbitMqProducer feeReminderRabbitMqProducer;

    public boolean sendGeneralCommunicationToParentsOfAllStudentsNotCompletedSchool(String generalAnnouncement) {
        try {
            List<Map<String, Object>> studentList = studentsService.fetchAllStudentsInEntireSchoolNotCompletedSchool();
            for (Map<String, Object> student : studentList) {
                if (student.get("ParentPhoneNumber") != null && !String.valueOf(student.get("ParentPhoneNumber")).isEmpty()) {
                    FeeReminderRmqCustomMessage message = new FeeReminderRmqCustomMessage();
                    message.setParentPhoneNumber(String.valueOf(student.get("ParentPhoneNumber")));
                    message.setGeneralAnnouncement(String.format("To %s's parent/guardian\n\n.%s"
                            , student.get("StudentName"), generalAnnouncement));
                    message.setMessageCategory(RabbitMqMessageDistinctionEnum.GENERAL_ANNOUNCEMENT);
                    feeReminderRabbitMqProducer.sendMessage(message);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendGeneralCommunicationToParentsOfAParticularLot(String generalAnnouncement, int lotId) {
        try {
            List<Map<String, Object>> studentList = studentsService.fetchStudentsOfAParticularLot(lotId);
            for (Map<String, Object> student : studentList) {
                if (student.get("ParentPhoneNumber") != null && !String.valueOf(student.get("ParentPhoneNumber")).isEmpty()) {
                    FeeReminderRmqCustomMessage message = new FeeReminderRmqCustomMessage();
                    message.setParentPhoneNumber(String.valueOf(student.get("ParentPhoneNumber")));
                    message.setGeneralAnnouncement(String.format("To %s's parent/guardian\n\n.%s"
                            , student.get("StudentName"), generalAnnouncement));
                    message.setMessageCategory(RabbitMqMessageDistinctionEnum.GENERAL_ANNOUNCEMENT);
                    feeReminderRabbitMqProducer.sendMessage(message);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendGeneralCommunicationToParentsOfAParticularClassStream(String generalAnnouncement, int classId) {
        try {
            List<Map<String, Object>> studentList = studentsService.fetchStudentsOfAParticularClassStream(classId);
            for (Map<String, Object> student : studentList) {
                if (student.get("ParentPhoneNumber") != null && !String.valueOf(student.get("ParentPhoneNumber")).isEmpty()) {
                    FeeReminderRmqCustomMessage message = new FeeReminderRmqCustomMessage();
                    message.setParentPhoneNumber(String.valueOf(student.get("ParentPhoneNumber")));
                    message.setGeneralAnnouncement(String.format("To %s's parent/guardian\n\n.%s"
                            , student.get("StudentName"), generalAnnouncement));
                    message.setMessageCategory(RabbitMqMessageDistinctionEnum.GENERAL_ANNOUNCEMENT);
                    feeReminderRabbitMqProducer.sendMessage(message);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendCommunicationToParentsOfSpecificListOfStudents(String generalAnnouncement, List<Integer> studentIds) {
        try {
            List<Map<String, Object>> studentList = studentsService.fetchStudentsFromAProvidedListOfStudentIds(studentIds);
            for (Map<String, Object> student : studentList) {
                if (student.get("ParentPhoneNumber") != null && !String.valueOf(student.get("ParentPhoneNumber")).isEmpty()) {
                    FeeReminderRmqCustomMessage message = new FeeReminderRmqCustomMessage();
                    message.setParentPhoneNumber(String.valueOf(student.get("ParentPhoneNumber")));
                    message.setGeneralAnnouncement(String.format("To %s's parent/guardian\n\n.%s"
                            , student.get("StudentName"), generalAnnouncement));
                    message.setMessageCategory(RabbitMqMessageDistinctionEnum.GENERAL_ANNOUNCEMENT);
                    feeReminderRabbitMqProducer.sendMessage(message);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
