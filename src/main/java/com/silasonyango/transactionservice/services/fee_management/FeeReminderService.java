package com.silasonyango.transactionservice.services.fee_management;

import com.silasonyango.transactionservice.dtos.rabbitmq.FeeReminderRmqCustomMessage;
import com.silasonyango.transactionservice.dtos.rabbitmq.RabbitMqMessageDistinctionEnum;
import com.silasonyango.transactionservice.services.rabbitmq.producer.FeeReminderRabbitMqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FeeReminderService {
    @Autowired
    FeeStatementService feeStatementService;

    @Autowired
    FeeReminderRabbitMqProducer feeReminderRabbitMqProducer;


    public boolean sendSmsFeeReminderToEntireSchoolNotCompletedSchoolWithThreshold(
            int feeBalanceThreshold, String paymentDeadlineDate) {
        try {
            List<Map<String, Object>> studentsToBeReminded = feeStatementService
                    .fetchFeeBalancesForEntireSchoolWithTermBalanceGreaterThanOrEqualProvidedAmount(
                            feeBalanceThreshold
                    );
            for (Map<String, Object> map : studentsToBeReminded) {
                if (map.get("ParentPhoneNumber") != null && !String.valueOf(map.get("ParentPhoneNumber")).isEmpty()) {
                    FeeReminderRmqCustomMessage message = new FeeReminderRmqCustomMessage(
                            Integer.parseInt(String.valueOf(map.get("StudentId"))),
                            String.valueOf(map.get("AdmissionNo")),
                            String.valueOf(map.get("StudentName")),
                            Integer.parseInt(String.valueOf(map.get("CurrentTermBalance"))),
                            Integer.parseInt(String.valueOf(map.get("AnnualBalance"))),
                            String.valueOf(map.get("ParentPhoneNumber")),
                            paymentDeadlineDate
                    );
                    message.setMessageCategory(RabbitMqMessageDistinctionEnum.FEE_REMINDER);
                    feeReminderRabbitMqProducer.sendMessage(message);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean sendSmsFeeReminderForSpecificLot(int lotId, int feeBalanceThreshold, String paymentDeadlineDate) {
        try {
            List<Map<String, Object>> studentsToBeReminded = feeStatementService
                    .fetchFeeBalancesForASpecificLotWithTermBalanceGreaterThanOrEqualProvidedAmount(
                            lotId,
                            feeBalanceThreshold
                    );
            for (Map<String, Object> map : studentsToBeReminded) {
                if (map.get("ParentPhoneNumber") != null && !String.valueOf(map.get("ParentPhoneNumber")).isEmpty()) {
                    FeeReminderRmqCustomMessage message = new FeeReminderRmqCustomMessage(
                            Integer.parseInt(String.valueOf(map.get("StudentId"))),
                            String.valueOf(map.get("AdmissionNo")),
                            String.valueOf(map.get("StudentName")),
                            Integer.parseInt(String.valueOf(map.get("CurrentTermBalance"))),
                            Integer.parseInt(String.valueOf(map.get("AnnualBalance"))),
                            String.valueOf(map.get("ParentPhoneNumber")),
                            paymentDeadlineDate
                    );
                    message.setMessageCategory(RabbitMqMessageDistinctionEnum.FEE_REMINDER);
                    feeReminderRabbitMqProducer.sendMessage(message);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean sendSmsFeeReminderForSpecificClassStream(int classId, int feeBalanceThreshold, String paymentDeadlineDate) {
        try {
            List<Map<String, Object>> studentsToBeReminded = feeStatementService
                    .fetchFeeBalancesForASpecificClassWithTermBalanceGreaterThanOrEqualProvidedAmount(
                            classId,
                            feeBalanceThreshold
                    );
            for (Map<String, Object> map : studentsToBeReminded) {
                if (map.get("ParentPhoneNumber") != null && !String.valueOf(map.get("ParentPhoneNumber")).isEmpty()) {
                    FeeReminderRmqCustomMessage message = new FeeReminderRmqCustomMessage(
                            Integer.parseInt(String.valueOf(map.get("StudentId"))),
                            String.valueOf(map.get("AdmissionNo")),
                            String.valueOf(map.get("StudentName")),
                            Integer.parseInt(String.valueOf(map.get("CurrentTermBalance"))),
                            Integer.parseInt(String.valueOf(map.get("AnnualBalance"))),
                            String.valueOf(map.get("ParentPhoneNumber")),
                            paymentDeadlineDate
                    );
                    message.setMessageCategory(RabbitMqMessageDistinctionEnum.FEE_REMINDER);
                    feeReminderRabbitMqProducer.sendMessage(message);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    public boolean sendSmsFeeReminderForSpecificListOfStudents(List<Integer> studentIds, String paymentDeadlineDate) {
        try {
            List<Map<String, Object>> studentsToBeReminded = feeStatementService
                    .fetchFeeBalancesForASpecificListOfStudents(
                            studentIds
                    );
            for (Map<String, Object> map : studentsToBeReminded) {
                if (map.get("ParentPhoneNumber") != null && !String.valueOf(map.get("ParentPhoneNumber")).isEmpty()) {
                    FeeReminderRmqCustomMessage message = new FeeReminderRmqCustomMessage(
                            Integer.parseInt(String.valueOf(map.get("StudentId"))),
                            String.valueOf(map.get("AdmissionNo")),
                            String.valueOf(map.get("StudentName")),
                            Integer.parseInt(String.valueOf(map.get("CurrentTermBalance"))),
                            Integer.parseInt(String.valueOf(map.get("AnnualBalance"))),
                            String.valueOf(map.get("ParentPhoneNumber")),
                            paymentDeadlineDate
                    );
                    message.setMessageCategory(RabbitMqMessageDistinctionEnum.FEE_REMINDER);
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
