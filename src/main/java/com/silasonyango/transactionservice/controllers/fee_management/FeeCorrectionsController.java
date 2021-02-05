package com.silasonyango.transactionservice.controllers.fee_management;

import com.silasonyango.transactionservice.common.config.CorrectionDescriptionsConfig;
import com.silasonyango.transactionservice.common.config.SessionActivitiesConfig;
import com.silasonyango.transactionservice.common.config.TransactionDescriptionsConfig;
import com.silasonyango.transactionservice.dtos.fee_management.InstallmentDeletionRequestDto;
import com.silasonyango.transactionservice.dtos.fee_management.InstallmentRequestDto;
import com.silasonyango.transactionservice.entity_classes.fee_management.FeeCorrectionsEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.FeeStatementEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.InstallmentsEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.TransactionsEntity;
import com.silasonyango.transactionservice.entity_classes.session_management.UserSessionActivitiesEntity;
import com.silasonyango.transactionservice.repository.correction_management.CorrectionDescriptionsRepository;
import com.silasonyango.transactionservice.repository.fee_management.*;
import com.silasonyango.transactionservice.repository.session_management.SessionActivitiesRepository;
import com.silasonyango.transactionservice.repository.session_management.UserSessionActivitiesRepository;
import com.silasonyango.transactionservice.repository.student_management.StudentRepository;
import com.silasonyango.transactionservice.repository.student_management.StudentResidenceRepository;
import com.silasonyango.transactionservice.utility_classes.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/fee_corrections")
public class FeeCorrectionsController {

    @Autowired
    InstallmentRepository installmentRepository;

    @Autowired
    TransactionsRepository transactionsRepository;

    @Autowired
    UserSessionActivitiesRepository userSessionActivitiesRepository;

    @Autowired
    SessionActivitiesRepository sessionActivitiesRepository;

    @Autowired
    TransactionDescriptionsRepository transactionDescriptionsRepository;

    @Autowired
    CarryForwardsRepository carryForwardsRepository;

    @Autowired
    FeeCorrectionsRepository feeCorrectionsRepository;

    @Autowired
    CorrectionDescriptionsRepository correctionDescriptionsRepository;

    @Autowired
    FeeStatementRepository feeStatementRepository;

    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/soft_delete_fee_installment")
    public void softDeleteFeeInstallment(@Valid InstallmentDeletionRequestDto installmentDeletionRequestDto) {
        InstallmentsEntity installmentToBeSoftDeleted = installmentRepository.findInstallmentByInstallmentId(installmentDeletionRequestDto.getInstallmentId());
        installmentToBeSoftDeleted.setHasBeenSoftDeleted(1);
        installmentToBeSoftDeleted.setInstallmentYear(installmentToBeSoftDeleted.getInstallmentYear().substring(0,4));
        installmentRepository.save(installmentToBeSoftDeleted);

        UserSessionActivitiesEntity userSessionActivitiesEntity = new UserSessionActivitiesEntity(
                installmentDeletionRequestDto.getSessionLogId(),
                sessionActivitiesRepository.findBySessionActivityCode(SessionActivitiesConfig.CORRECT_A_FEE_PAYMENT_SESSION_ACTIVITY).get(0).getSessionActivityId(),
                UtilityClass.getNow(),
                0
        );

        userSessionActivitiesEntity = userSessionActivitiesRepository.save(userSessionActivitiesEntity);


        FeeStatementEntity feeStatementEntity = feeStatementRepository.findFeeStatementByStudentId(installmentToBeSoftDeleted.getStudentId()).get(0);
        int previousTermBalance = feeStatementEntity.getCurrentTermBalance();
        int previousAnnualBalance = feeStatementEntity.getAnnualBalance();
        int previousTotal = feeStatementEntity.getCurrentYearTotal();
        feeStatementEntity.setCurrentTermBalance(adjustTermBalance(installmentToBeSoftDeleted.getInstallmentAmount(), feeStatementEntity));
        feeStatementEntity.setCurrentYearTotal(adjustYearTotal(installmentToBeSoftDeleted.getInstallmentAmount(), feeStatementEntity));
        feeStatementEntity.setAnnualBalance(UtilityClass.getAStudentAnnualBalanceFromTermBalance(installmentToBeSoftDeleted.getStudentId(),
                feeStatementEntity.getCurrentTermBalance(),
                studentRepository.findByStudentId(installmentToBeSoftDeleted.getStudentId()).get(0).getStudentResidenceId()));

        feeStatementRepository.save(feeStatementEntity);

        FeeCorrectionsEntity feeCorrectionsEntity = feeCorrectionsRepository.save(new FeeCorrectionsEntity(
                installmentDeletionRequestDto.getSessionLogId(),
                userSessionActivitiesEntity.getUserSessionActivityId(),
//todo: fix correction description
//                correctionDescriptionsRepository.findByCorrectionDescriptionCode(CorrectionDescriptionsConfig.DELETE_FEE_INSTALLMENT).getCorrectionDescriptionId(),
                10,
                installmentToBeSoftDeleted.getStudentId(),
                previousTermBalance,
                previousAnnualBalance,
                previousTotal,
                feeStatementEntity.getCurrentTermBalance(),
                feeStatementEntity.getAnnualBalance(),
                feeStatementEntity.getCurrentYearTotal(),
                UtilityClass.getNow(),
                0
        ));


        transactionsRepository.save(new TransactionsEntity(installmentDeletionRequestDto.getSessionLogId(),
                userSessionActivitiesEntity.getUserSessionActivityId(),
                transactionDescriptionsRepository.findByTransactionDescriptionCode(TransactionDescriptionsConfig.FEE_CORRECTION_TRANSACTION_DESCRIPTION).get(0).getTransactionDescriptionId(),
                installmentToBeSoftDeleted.getStudentId(),
                installmentToBeSoftDeleted.getInstallmentId(),
                carryForwardsRepository.findByIsAdminCarryForward(1).get(0).getCarryFowardId(),
                feeCorrectionsEntity.getFeeCorrectionId(),
                previousTermBalance,
                previousAnnualBalance,
                previousTotal,
                feeStatementEntity.getCurrentTermBalance(),
                feeStatementEntity.getAnnualBalance(),
                feeStatementEntity.getCurrentYearTotal(),
                UtilityClass.getNow()));
    }

    public int adjustTermBalance(int installmentAmount, FeeStatementEntity feeStatement) {
        return feeStatement.getCurrentTermBalance() + installmentAmount;
    }

    public int adjustYearTotal(int installmentAmount, FeeStatementEntity feeStatement) {
        return feeStatement.getCurrentYearTotal() - installmentAmount;
    }
}
