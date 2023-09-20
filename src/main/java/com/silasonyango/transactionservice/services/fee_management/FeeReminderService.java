package com.silasonyango.transactionservice.services.fee_management;

import com.silasonyango.transactionservice.dtos.rabbitmq.FeeReminderRmqCustomMessage;
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

    public boolean sendSmsFeeReminderForSpecificLot(int lotId, int feeBalanceThreshold) {
        try {
            List<Map<String, Object>> studentsToBeReminded = feeStatementService
                    .fetchFeeBalancesForASpecificLotWithTermBalanceGreaterThanOrEqualProvidedAmount(
                            lotId,
                            feeBalanceThreshold
                    );
            for (Map<String, Object> map : studentsToBeReminded) {
                FeeReminderRmqCustomMessage message = new FeeReminderRmqCustomMessage(
                        Integer.parseInt(String.valueOf(map.get("StudentId"))),
                        String.valueOf(map.get("AdmissionNo")),
                        String.valueOf(map.get("StudentName")),
                        Integer.parseInt(String.valueOf(map.get("CurrentTermBalance"))),
                        Integer.parseInt(String.valueOf(map.get("AnnualBalance"))),
                        map.get("ParentPhoneNumber") != null && !String.valueOf(map.get("ParentPhoneNumber")).isEmpty()
                                ? String.valueOf(map.get("ParentPhoneNumber")) : null
                );
                feeReminderRabbitMqProducer.sendMessage(message);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
