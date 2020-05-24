package com.silasonyango.transactionservice.controllers.fee_management;

import com.silasonyango.transactionservice.common.config.SessionActivitiesConfig;
import com.silasonyango.transactionservice.dtos.fee_management.FeeStatementResponseDto;
import com.silasonyango.transactionservice.dtos.fee_management.InstallmentsDto;
import com.silasonyango.transactionservice.entity_classes.fee_management.FeeStatementEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.InstallmentsEntity;
import com.silasonyango.transactionservice.repository.fee_management.FeeStatementRepository;
import com.silasonyango.transactionservice.repository.fee_management.InstallmentRepository;
import com.silasonyango.transactionservice.utility_classes.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/installments")
public class InstallmentsController {
    @Autowired
    InstallmentRepository installmentRepository;

    @Autowired
    FeeStatementRepository feeStatementRepository;

    @PostMapping("/add_installment")
    public FeeStatementResponseDto addInstallment(@Valid InstallmentsDto installmentsDto) {

        InstallmentsEntity dbInstallment = installmentRepository.save(new InstallmentsEntity(installmentsDto.getStudentId(),installmentsDto.getInstallmentAmount(), UtilityClass.getNow(),0,installmentsDto.getSessionLogId(), SessionActivitiesConfig.REGISTER_FEE_INSTALLMENT_SESSION_ACTIVITY,UtilityClass.getCurrentYear()));
    }

    public int getNextYearTotalFromInstallment(int studentId,int installmentAmount) {

        FeeStatementEntity dbFeeStatement = feeStatementRepository.findFeeStatementByStudentId(studentId).get(0);

        return dbFeeStatement.getCurrentYearTotal() + installmentAmount;
    }

    public int getNextAlternateTotal(int studentId) {
        int alternateTotal = 0;

        List<InstallmentsEntity> dbInstallmentsList = installmentRepository.findInstallmentsByStudentId(studentId);

        for(int i = 0; i < dbInstallmentsList.size(); i++) {

            if(dbInstallmentsList.get(i).getInstallmentYear().equals(UtilityClass.getCurrentYear())) {
                alternateTotal = alternateTotal + dbInstallmentsList.get(i).getInstallmentAmount();
            }

        }

        return alternateTotal;
    }

    public int getNextTermBalance(int studentId,int installmentAmount) {
        FeeStatementEntity dbFeeStatement = feeStatementRepository.findFeeStatementByStudentId(studentId).get(0);

        return dbFeeStatement.getCurrentTermBalance() - installmentAmount;
    }
}
