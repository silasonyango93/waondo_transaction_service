package com.silasonyango.transactionservice.services.fee_statement;

import com.silasonyango.transactionservice.repository.fee_management.FeeCorrectionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeeCorrectionsService {

    @Autowired
    FeeCorrectionsRepository feeCorrectionsRepository;

    public boolean deleteFeeCorrectionsByStudentId(int studentId) {
        try {
            feeCorrectionsRepository.deleteByStudentId(studentId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
