package com.silasonyango.transactionservice.services.fee_statement;

import com.silasonyango.transactionservice.repository.fee_management.CarryForwardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarryForwardsService {

    @Autowired
    CarryForwardsRepository carryForwardsRepository;

    public boolean deleteCarryForwardsByStudentId(int studentId) {
        try {
            carryForwardsRepository.deleteByStudentId(studentId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
