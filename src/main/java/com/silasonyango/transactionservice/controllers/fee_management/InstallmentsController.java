package com.silasonyango.transactionservice.controllers.fee_management;

import com.silasonyango.transactionservice.common.config.SessionActivitiesConfig;
import com.silasonyango.transactionservice.dtos.fee_management.FeeStatementResponseDto;
import com.silasonyango.transactionservice.dtos.fee_management.InstallmentsDto;
import com.silasonyango.transactionservice.entity_classes.fee_management.InstallmentsEntity;
import com.silasonyango.transactionservice.repository.fee_management.InstallmentRepository;
import com.silasonyango.transactionservice.utility_classes.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/installments")
public class InstallmentsController {
    @Autowired
    InstallmentRepository installmentRepository;

    @PostMapping("/add_installment")
    public FeeStatementResponseDto addInstallment(@Valid InstallmentsDto installmentsDto) {

        InstallmentsEntity dbInstallment = installmentRepository.save(new InstallmentsEntity(installmentsDto.getStudentId(),installmentsDto.getInstallmentAmount(), UtilityClass.getNow(),0,installmentsDto.getSessionLogId(), SessionActivitiesConfig.REGISTER_FEE_INSTALLMENT_SESSION_ACTIVITY));
    }
}
