package com.silasonyango.transactionservice.controllers.fee_management;

import com.silasonyango.transactionservice.common.config.SessionActivitiesConfig;
import com.silasonyango.transactionservice.common.config.TransactionDescriptionsConfig;
import com.silasonyango.transactionservice.dtos.fee_management.FeeComponentsResponseDto;
import com.silasonyango.transactionservice.dtos.fee_management.FeeStatementResponseDto;
import com.silasonyango.transactionservice.dtos.fee_management.InstallmentsDto;
import com.silasonyango.transactionservice.dtos.fee_management.InstallmentsResponseDto;
import com.silasonyango.transactionservice.entity_classes.fee_management.FeeStatementEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.InstallmentsEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.StudentFeeComponentEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.TransactionsEntity;
import com.silasonyango.transactionservice.entity_classes.session_management.UserSessionActivitiesEntity;
import com.silasonyango.transactionservice.entity_classes.student_management.StudentEntity;
import com.silasonyango.transactionservice.repository.fee_management.FeeStatementRepository;
import com.silasonyango.transactionservice.repository.fee_management.InstallmentRepository;
import com.silasonyango.transactionservice.repository.fee_management.StudentFeeComponentRepository;
import com.silasonyango.transactionservice.repository.fee_management.TransactionsRepository;
import com.silasonyango.transactionservice.repository.session_management.UserSessionActivitiesRepository;
import com.silasonyango.transactionservice.repository.student_management.StudentRepository;
import com.silasonyango.transactionservice.utility_classes.UtilityClass;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.silasonyango.transactionservice.utility_classes.UtilityClass.getTermDetailsByDate;

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

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentFeeComponentRepository studentFeeComponentRepository;

    @PostMapping("/add_installment")
    public FeeStatementResponseDto addInstallment(@Valid InstallmentsDto installmentsDto) {

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

        feeStatementRepository.save(dbFeeStatement);

        updateAStudentFeeComponents(dbFeeStatement.getStudentId(),dbFeeStatement.getCurrentYearTotal());

        return getAStudentFeeStatementForCurrentYear(dbFeeStatement.getStudentId());
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
        JSONObject termObject = getTermDetailsByDate(UtilityClass.getToday());

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



    public FeeStatementResponseDto getAStudentFeeStatementForCurrentYear(int studentId) {

        FeeStatementResponseDto feeStatementResponseDto = new FeeStatementResponseDto();

        StudentEntity studentPersonalDetails = studentRepository.findByStudentId(studentId).get(0);
        JSONObject classDetails = UtilityClass.getAStudentClassDetails(studentId);
        JSONObject residenceDetails = UtilityClass.getAStudentResidenceDetails(studentId);
        FeeStatementEntity feeStatementEntity = feeStatementRepository.findFeeStatementByStudentId(studentId).get(0);
        List<InstallmentsEntity> feeInstallmentsList = installmentRepository.findInstallmentsByStudentId(studentId);


        feeStatementResponseDto.setStudentId(studentId);
        feeStatementResponseDto.setAdmissionNumber(studentPersonalDetails.getAdmissionNo());
        feeStatementResponseDto.setStudentName(studentPersonalDetails.getStudentName());
        feeStatementResponseDto.setGender(studentPersonalDetails.getGenderId() == 1 ? "Male" : "Female");
        feeStatementResponseDto.setClassDetails(classDetails.getString("AcademicClassLevelName") +" "+classDetails.getString("ClassStreamName"));
        feeStatementResponseDto.setResidenceDetails(residenceDetails.getString("StudentResidenceDescription"));
        feeStatementResponseDto.setTermBalance(feeStatementEntity.getCurrentTermBalance());
        feeStatementResponseDto.setAnnualBalance(feeStatementEntity.getAnnualBalance());
        feeStatementResponseDto.setCurrentyearTotal(feeStatementEntity.getCurrentYearTotal());

        List<InstallmentsResponseDto> installmentsResponseDtoArrayList = new ArrayList<>();


        for(int i = 0;i<feeInstallmentsList.size();i++) {
            if(feeInstallmentsList.get(i).getInstallmentYear().matches(".*?\\b" +UtilityClass.getCurrentYear()+ "\\b.*?")) {


                String installmentDate = feeInstallmentsList.get(i).getInstallmentDate().replaceAll(" .+$", "");

                installmentsResponseDtoArrayList.add(new InstallmentsResponseDto(feeInstallmentsList.get(i).getStudentId(),feeInstallmentsList.get(i).getInstallmentAmount(),installmentDate,feeInstallmentsList.get(i).getIsCarryForward(),feeInstallmentsList.get(i).getSessionLogId(),feeInstallmentsList.get(i).getUserSessionActivityId(),feeInstallmentsList.get(i).getInstallmentYear(),UtilityClass.getAUserByASessionLogId(feeInstallmentsList.get(i).getSessionLogId()).getString("Name"),getTermDetailsByDate(installmentDate).getString("TermIterationDescription")));

            }
        }

        feeStatementResponseDto.setInstallmentsResponseArray(installmentsResponseDtoArrayList);



        JSONArray feeComponentsArray = UtilityClass.getAStudentFeeComponents(studentId);
        List<FeeComponentsResponseDto> feeComponentsResponseDtoList = new ArrayList<>();

        for(int i = 0;i<feeComponentsArray.length();i++) {

            String feeComponentDescription = feeComponentsArray.getJSONObject(i).getString("FeeComponentDescription");
            double componentFeeAmount = feeComponentsArray.getJSONObject(i).getDouble("ComponentFeeAmount");

            feeComponentsResponseDtoList.add(new FeeComponentsResponseDto(feeComponentDescription,componentFeeAmount));

        }

        feeStatementResponseDto.setFeeComponentsResponseDtoList(feeComponentsResponseDtoList);
        feeStatementResponseDto.setFeeStatementProcessedSuccessfully(true);

        return feeStatementResponseDto;
    }


    public void updateAStudentFeeComponents(int studentId, int currentYearTotal) {

        JSONArray feeComponentsArray = UtilityClass.getAStudentFeeComponents(studentId);

        for(int i = 0;i<feeComponentsArray.length();i++) {

            int feeComponentRatio = feeComponentsArray.getJSONObject(i).getInt("FeeComponentRatio");
            int studentFeeComponentId = feeComponentsArray.getJSONObject(i).getInt("StudentFeeComponentId");
            int classFeeStructureComponentId = feeComponentsArray.getJSONObject(i).getInt("ClassFeeStructureComponentId");

            double ratio = (double)feeComponentRatio / 100;
            double componentFeeAmount = currentYearTotal * ratio;

            studentFeeComponentRepository.save(new StudentFeeComponentEntity(studentFeeComponentId,studentId,classFeeStructureComponentId,componentFeeAmount));
        }
    }

}