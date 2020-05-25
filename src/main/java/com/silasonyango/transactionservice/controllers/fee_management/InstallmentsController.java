package com.silasonyango.transactionservice.controllers.fee_management;

import com.silasonyango.transactionservice.common.config.SessionActivitiesConfig;
import com.silasonyango.transactionservice.common.config.TransactionDescriptionsConfig;
import com.silasonyango.transactionservice.dtos.fee_management.FeeStatementResponseDto;
import com.silasonyango.transactionservice.dtos.fee_management.InstallmentsDto;
import com.silasonyango.transactionservice.entity_classes.fee_management.FeeStatementEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.InstallmentsEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.TransactionsEntity;
import com.silasonyango.transactionservice.entity_classes.session_management.UserSessionActivitiesEntity;
import com.silasonyango.transactionservice.repository.fee_management.FeeStatementRepository;
import com.silasonyango.transactionservice.repository.fee_management.InstallmentRepository;
import com.silasonyango.transactionservice.repository.fee_management.TransactionsRepository;
import com.silasonyango.transactionservice.repository.session_management.UserSessionActivitiesRepository;
import com.silasonyango.transactionservice.utility_classes.UtilityClass;
import org.json.JSONArray;
import org.json.JSONObject;
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

    @Autowired
    UserSessionActivitiesRepository userSessionActivitiesRepository;

    @Autowired
    TransactionsRepository transactionsRepository;

    @PostMapping("/add_installment")
    public FeeStatementEntity addInstallment(@Valid InstallmentsDto installmentsDto) {

        int previousTermBalance = 0;
        int previousAnnualBalance = 0;
        int previousTotal = 0;

        FeeStatementEntity feeStatementBeforeTransaction = feeStatementRepository.findFeeStatementByStudentId(installmentsDto.getStudentId()).get(0);
        previousTermBalance = feeStatementBeforeTransaction.getCurrentTermBalance();
        previousAnnualBalance = feeStatementBeforeTransaction.getAnnualBalance();
        previousTotal = feeStatementBeforeTransaction.getCurrentYearTotal();

        UserSessionActivitiesEntity userSessionActivitiesEntity = userSessionActivitiesRepository.save(new UserSessionActivitiesEntity(installmentsDto.getSessionLogId(),SessionActivitiesConfig.REGISTER_FEE_INSTALLMENT_SESSION_ACTIVITY,UtilityClass.getNow()));

        InstallmentsEntity dbInstallment = installmentRepository.save(new InstallmentsEntity(installmentsDto.getStudentId(),installmentsDto.getInstallmentAmount(), UtilityClass.getNow(),0,installmentsDto.getSessionLogId(), userSessionActivitiesEntity.getUserSessionActivityId(),UtilityClass.getCurrentYear()));

        FeeStatementEntity dbFeeStatement = feeStatementRepository.findFeeStatementByStudentId(installmentsDto.getStudentId()).get(0);

        dbFeeStatement.setCurrentYearTotal(getNextYearTotalFromInstallment(dbFeeStatement.getStudentId(),installmentsDto.getInstallmentAmount()));
        dbFeeStatement.setAlternateTotal(getNextAlternateTotal(dbFeeStatement.getStudentId()));
        dbFeeStatement.setCurrentTermBalance(getNextTermBalance(dbFeeStatement.getStudentId(),installmentsDto.getInstallmentAmount()));
        dbFeeStatement.setAnnualBalance(UtilityClass.getAStudentAnnualBalanceFromTermBalance(dbFeeStatement.getStudentId(),dbFeeStatement.getCurrentTermBalance(),UtilityClass.getAStudentResidenceDetails(dbFeeStatement.getStudentId()).getInt("StudentResidenceId")));
        dbFeeStatement.setStudentWorth(getNextStudentWorth(dbFeeStatement.getStudentId()));

        registerTransaction(installmentsDto.getSessionLogId(),userSessionActivitiesEntity.getUserSessionActivityId(),installmentsDto.getStudentId(),dbInstallment.getInstallmentId(),previousTermBalance,previousAnnualBalance,previousTotal,dbFeeStatement.getCurrentTermBalance(),dbFeeStatement.getAnnualBalance(),dbFeeStatement.getCurrentYearTotal());

        return feeStatementRepository.save(dbFeeStatement);
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



    public int getNextStudentWorth(int studentId) {
        int studentWorth = 0;
        JSONObject termObject = UtilityClass.getTermDetailsByDate(UtilityClass.getToday());

        JSONArray installmentArray = UtilityClass.getInstallmentsForParticularStudentBetweenACertainPeriod(studentId,termObject.getString("TermStartDate"),termObject.getString("TermEndDate"));

        for(int i = 0;i<installmentArray.length();i++) {

            studentWorth = studentWorth + installmentArray.getJSONObject(i).getInt("InstallmentAmount");

        }

        return studentWorth;
    }


    public void registerTransaction(int sessionLogId,int userSessionActivityId,int studentId,int installmentId,int previousTermBalance,int previousAnnualBalance,int previousTotal,int nextTermBalance,int nextAnnualBalance,int nextTotal) {

        TransactionsEntity transaction = new TransactionsEntity();

        transaction.setSessionLogId(sessionLogId);
        transaction.setUserSessionActivityId(userSessionActivityId);
        transaction.setTransactionDescriptionId(TransactionDescriptionsConfig.REGISTER_FEE_INSTALLMENT_TRANSACTION_DESCRIPTION);
        transaction.setStudentId(studentId);
        transaction.setInstallmentId(installmentId);
        transaction.setPreviousTermBalance(previousTermBalance);
        transaction.setPreviousAnnualBalance(previousAnnualBalance);
        transaction.setPreviousTotal(previousTotal);
        transaction.setNextTermBalance(nextTermBalance);
        transaction.setNextAnnualBalance(nextAnnualBalance);
        transaction.setNextTotal(nextTotal);
        transaction.setTransactionDate(UtilityClass.getNow());

        transactionsRepository.save(transaction);
    }
}
