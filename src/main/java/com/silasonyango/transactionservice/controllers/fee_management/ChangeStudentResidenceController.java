package com.silasonyango.transactionservice.controllers.fee_management;

import com.silasonyango.transactionservice.common.config.ResidenceSwapCodes;
import com.silasonyango.transactionservice.common.config.SessionActivitiesConfig;
import com.silasonyango.transactionservice.common.config.TransactionDescriptionsConfig;
import com.silasonyango.transactionservice.common.config.UtilityConfigs;
import com.silasonyango.transactionservice.dtos.fee_management.FeeStatementResponseDto;
import com.silasonyango.transactionservice.dtos.student_management.StudentRequestByStudentIdDto;
import com.silasonyango.transactionservice.dtos.student_residence.ConfirmResidenceSwapResponse;
import com.silasonyango.transactionservice.entity_classes.fee_management.FeeStatementEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.TransactionsEntity;
import com.silasonyango.transactionservice.entity_classes.session_management.UserSessionActivitiesEntity;
import com.silasonyango.transactionservice.entity_classes.student_management.StudentEntity;
import com.silasonyango.transactionservice.entity_classes.student_residence.ResidenceSwapEntity;
import com.silasonyango.transactionservice.repository.fee_management.*;
import com.silasonyango.transactionservice.repository.session_management.SessionActivitiesRepository;
import com.silasonyango.transactionservice.repository.session_management.UserSessionActivitiesRepository;
import com.silasonyango.transactionservice.repository.student_management.StudentRepository;
import com.silasonyango.transactionservice.repository.student_management.StudentResidenceRepository;
import com.silasonyango.transactionservice.repository.student_residence.ResidenceSwapRepository;
import com.silasonyango.transactionservice.utility_classes.UtilityClass;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/change_student_residence")
public class ChangeStudentResidenceController {
    @Autowired
    InstallmentsController installmentsController;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentResidenceRepository studentResidenceRepository;

    @Autowired
    FeeStatementRepository feeStatementRepository;

    @Autowired
    ResidenceSwapRepository residenceSwapRepository;

    @Autowired
    UserSessionActivitiesRepository userSessionActivitiesRepository;

    @Autowired
    SessionActivitiesRepository sessionActivitiesRepository;

    @Autowired
    TransactionsRepository transactionsRepository;

    @Autowired
    InstallmentRepository installmentRepository;

    @Autowired
    CarryForwardsRepository carryForwardsRepository;

    @Autowired
    FeeCorrectionsRepository feeCorrectionsRepository;

    @Autowired
    TransactionDescriptionsRepository transactionDescriptionsRepository;

    @PostMapping("/confirm_residence_swap")
    public ConfirmResidenceSwapResponse confirmResidenceSwapPeriodEligibility(@Valid StudentRequestByStudentIdDto studentRequestByStudentIdDto) {
        studentRequestByStudentIdDto.setStudentId(studentRepository.findByAdmissionNo(studentRequestByStudentIdDto.getAdmissionNo()).get(0).getStudentId());
        ConfirmResidenceSwapResponse confirmResidenceSwapResponse = new ConfirmResidenceSwapResponse();
        JSONObject currentTermObject = UtilityClass.getTermDetailsByDate(UtilityClass.getToday());
        JSONObject currentWeekObject = UtilityClass.getTheCurrentWeek(UtilityClass.getToday());

        if (currentTermObject == null) {
            confirmResidenceSwapResponse.setPeriodEligible(false);
            confirmResidenceSwapResponse.setEligibilityMessage("The change residence module does not work over the holidays. Kindly wait till the begining of the next term");
        } else if (currentTermObject != null && currentWeekObject != null && currentWeekObject.getInt("WeekIterationCode") > 5) {
            confirmResidenceSwapResponse.setPeriodEligible(false);
            confirmResidenceSwapResponse.setEligibilityMessage("You cannot change a student's residence more than 5 weeks into the term");
        } else {
            JSONObject studentResidenceDetails = UtilityClass.getAStudentResidenceDetails(studentRequestByStudentIdDto.getStudentId());
            confirmResidenceSwapResponse.setAdmissionNumber(studentRequestByStudentIdDto.getAdmissionNo());
            confirmResidenceSwapResponse.setPeriodEligible(true);
            confirmResidenceSwapResponse.setEligibilityMessage("Change of residence in this period is allowed");
            confirmResidenceSwapResponse.setCurrentResidenceCode(studentResidenceDetails.getInt("StudentResidenceCode"));
            confirmResidenceSwapResponse.setProposedResidenceCode(studentResidenceDetails.getInt("StudentResidenceCode") == 1 ? 2 : 1);
            FeeStatementResponseDto feeStatementResponseDto = installmentsController.getAStudentFeeStatementForCurrentYear(studentRequestByStudentIdDto.getStudentId());
            confirmResidenceSwapResponse.setCurrentTermBalance((double) feeStatementResponseDto.getTermBalance());
            confirmResidenceSwapResponse.setCurrentAnnualBalance((double) feeStatementResponseDto.getAnnualBalance());
            confirmResidenceSwapResponse.setChangeExtraCharge(confirmResidenceSwapResponse.getProposedResidenceCode() == 1 ? UtilityConfigs.CHANGE_TO_BOARDING_EXTRA_CHARGE : 0.0);

            double currentTermFee = 0.0;
            double replacementTermFee = 0.0;
            JSONObject classDetails = UtilityClass.getAStudentClassDetails(studentRequestByStudentIdDto.getStudentId());
            JSONArray currentFeeStructureArray = UtilityClass.getFeeStructureForParticularClassLevel(classDetails.getInt("AcademicClassLevelId"), studentRepository.findByStudentId(studentRequestByStudentIdDto.getStudentId()).get(0).getStudentResidenceId());

            JSONObject currentTermFeeObject = null;
            for (int i = 0; i < currentFeeStructureArray.length(); i++) {
                if (currentFeeStructureArray.getJSONObject(i).getInt("TermIterationId") == currentTermObject.getInt("TermIterationId")) {
                    currentTermFeeObject = currentFeeStructureArray.getJSONObject(i);
                }
            }

            currentTermFee = (double) currentTermFeeObject.getInt("FeeAmount");


            JSONArray proposedFeeStructureArray = UtilityClass.getFeeStructureForParticularClassLevel(classDetails.getInt("AcademicClassLevelId"), studentResidenceRepository.findByStudentResidenceCode(confirmResidenceSwapResponse.getProposedResidenceCode()).get(0).getStudentResidenceId());
            JSONObject proposedTermFeeObject = null;
            for (int i = 0; i < proposedFeeStructureArray.length(); i++) {
                if (proposedFeeStructureArray.getJSONObject(i).getInt("TermIterationId") == currentTermObject.getInt("TermIterationId")) {
                    proposedTermFeeObject = proposedFeeStructureArray.getJSONObject(i);
                }
            }

            replacementTermFee = (double) proposedTermFeeObject.getInt("FeeAmount");


            if (confirmResidenceSwapResponse.getProposedResidenceCode() == 1) {
                double tempTermBalance = confirmResidenceSwapResponse.getCurrentTermBalance();

                tempTermBalance = tempTermBalance - currentTermFee;
                tempTermBalance = tempTermBalance + replacementTermFee;
                tempTermBalance = tempTermBalance + UtilityConfigs.CHANGE_TO_BOARDING_EXTRA_CHARGE;
                confirmResidenceSwapResponse.setExpectedTermBalance(tempTermBalance);
                confirmResidenceSwapResponse.setExpectedAnnualBalance((double) UtilityClass.getAStudentAnnualBalanceFromTermBalance(studentRequestByStudentIdDto.getStudentId(), (int) confirmResidenceSwapResponse.getExpectedTermBalance(), studentResidenceRepository.findByStudentResidenceCode(confirmResidenceSwapResponse.getProposedResidenceCode()).get(0).getStudentResidenceId()));
            } else {
                double tempTermBalance = confirmResidenceSwapResponse.getCurrentTermBalance();

                tempTermBalance = tempTermBalance - currentTermFee;
                tempTermBalance = tempTermBalance + replacementTermFee;
                confirmResidenceSwapResponse.setExpectedTermBalance(tempTermBalance);
                confirmResidenceSwapResponse.setExpectedAnnualBalance((double) UtilityClass.getAStudentAnnualBalanceFromTermBalance(studentRequestByStudentIdDto.getStudentId(), (int) confirmResidenceSwapResponse.getExpectedTermBalance(), studentResidenceRepository.findByStudentResidenceCode(confirmResidenceSwapResponse.getProposedResidenceCode()).get(0).getStudentResidenceId()));
            }
        }

        return confirmResidenceSwapResponse;
    }

    @PostMapping("/execute_residence_swap")
    public void executeResidenceSwap(@Valid ConfirmResidenceSwapResponse confirmResidenceSwapResponse) {
        int studentId = studentRepository.findByAdmissionNo(confirmResidenceSwapResponse.getAdmissionNumber()).get(0).getStudentId();
        FeeStatementEntity feeStatementEntity = feeStatementRepository.findFeeStatementByStudentId(studentId).get(0);
        feeStatementEntity.setCurrentTermBalance((int) confirmResidenceSwapResponse.getExpectedTermBalance());
        feeStatementEntity.setAnnualBalance((int) confirmResidenceSwapResponse.getExpectedAnnualBalance());
        feeStatementRepository.save(feeStatementEntity);

        StudentEntity studentEntity = studentRepository.findByAdmissionNo(confirmResidenceSwapResponse.getAdmissionNumber()).get(0);
        studentEntity.setStudentResidenceId(determineStudentResidence(confirmResidenceSwapResponse.getProposedResidenceCode()));
        studentRepository.save(studentEntity);

        residenceSwapRepository.save(new ResidenceSwapEntity(
                determineResidenceSwapCode(confirmResidenceSwapResponse.getProposedResidenceCode()),
                confirmResidenceSwapResponse.getSessionLogId(),
                studentId,
                UtilityClass.getNow()
        ));

        UserSessionActivitiesEntity userSessionActivitiesEntity = userSessionActivitiesRepository.save(new UserSessionActivitiesEntity(
                confirmResidenceSwapResponse.getSessionLogId(),
                sessionActivitiesRepository.findBySessionActivityCode(determineUserSessionActivity(confirmResidenceSwapResponse.getProposedResidenceCode())).get(0).getSessionActivityId(),
                UtilityClass.getNow(),
                0
        ));

        transactionsRepository.save(new TransactionsEntity(
                confirmResidenceSwapResponse.getSessionLogId(),
                userSessionActivitiesEntity.getUserSessionActivityId(),
                transactionDescriptionsRepository.findByTransactionDescriptionCode(determineTransactionDescription(confirmResidenceSwapResponse.getProposedResidenceCode())).get(0).getTransactionDescriptionId(),
                studentId,
                getAdminInstallmentId(),
                getAdminCarryForwardId(),
                getAdminFeeCorrectionId(),
                (int) confirmResidenceSwapResponse.getCurrentTermBalance(),
                (int) confirmResidenceSwapResponse.getCurrentAnnualBalance(),
                feeStatementEntity.getCurrentYearTotal(),
                feeStatementEntity.getCurrentTermBalance(),
                feeStatementEntity.getAnnualBalance(),
                feeStatementEntity.getCurrentYearTotal(),
                UtilityClass.getNow()
        ));
    }

    public int determineResidenceSwapCode(int proposedResidenceCode) {
        return proposedResidenceCode == 1 ? ResidenceSwapCodes.TO_BOARDER_SWAP_CODE : ResidenceSwapCodes.TO_DAY_SCHOLAR_SWAP_CODE;
    }

    public int determineUserSessionActivity (int proposedResidenceCode) {
        return proposedResidenceCode == 1 ? SessionActivitiesConfig.CHANGE_STUDENT_RESIDENCE_TO_BOADING : SessionActivitiesConfig.CHANGE_STUDENT_RESIDENCE_TO_DAY_SCHOOL;
    }

    public int determineTransactionDescription(int proposedResidenceCode) {
        return proposedResidenceCode == 1 ? TransactionDescriptionsConfig.TO_BOARDER_RESIDENCE_SWAP : TransactionDescriptionsConfig.TO_DAY_SCHOOL_RESIDENCE_SWAP;
    }

    public int determineStudentResidence(int proposedResidenceCode) {
        return studentResidenceRepository.findByStudentResidenceCode(proposedResidenceCode).get(0).getStudentResidenceId();
    }

    public int getAdminInstallmentId() {
        return installmentRepository.findInstallmentByIsAdminInstallment(1).get(0).getInstallmentId();
    }

    public int getAdminCarryForwardId() {
        return carryForwardsRepository.findByIsAdminCarryForward(1).get(0).getCarryFowardId();
    }

    public int getAdminFeeCorrectionId() {
        return feeCorrectionsRepository.findByIsAdminFeeCorrection(1).get(0).getFeeCorrectionId();
    }
}
