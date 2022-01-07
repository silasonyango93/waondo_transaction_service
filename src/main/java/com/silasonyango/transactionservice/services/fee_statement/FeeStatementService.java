package com.silasonyango.transactionservice.services.fee_statement;

import com.silasonyango.transactionservice.controllers.fee_management.InstallmentsController;
import com.silasonyango.transactionservice.dtos.fee_management.FeeStatementResponseDto;
import com.silasonyango.transactionservice.entity_classes.fee_management.FeeStatementEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.InstallmentsEntity;
import com.silasonyango.transactionservice.repository.fee_management.FeeStatementRepository;
import com.silasonyango.transactionservice.repository.fee_management.InstallmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeStatementService {

    @Autowired
    FeeStatementRepository feeStatementRepository;

    @Autowired
    InstallmentRepository installmentRepository;

    @Autowired
    InstallmentsController installmentsController;

    public FeeStatementEntity getAStudentFeeStatement(int studentId) {
        return feeStatementRepository.findFeeStatementByStudentId(studentId).get(0);
    }

    public FeeStatementResponseDto getAStudentFeeStatementSinceJoining(int studentId) {
        return installmentsController.getAStudentFeeStatementSinceJoining(studentId);
    }

    public FeeStatementResponseDto getAStudentFeeStatementForCurrentYear(int studentId) {
        return installmentsController.getAStudentFeeStatementForCurrentYear(studentId);
    }
}
