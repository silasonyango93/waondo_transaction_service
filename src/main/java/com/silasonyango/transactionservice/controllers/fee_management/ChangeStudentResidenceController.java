package com.silasonyango.transactionservice.controllers.fee_management;

import com.silasonyango.transactionservice.common.config.UtilityConfigs;
import com.silasonyango.transactionservice.dtos.fee_management.FeeStatementResponseDto;
import com.silasonyango.transactionservice.dtos.student_management.StudentRequestByStudentIdDto;
import com.silasonyango.transactionservice.dtos.student_residence.ConfirmResidenceSwapResponse;
import com.silasonyango.transactionservice.repository.student_management.StudentRepository;
import com.silasonyango.transactionservice.repository.student_management.StudentResidenceRepository;
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

    @PostMapping("/confirm_residence_swap")
    public ConfirmResidenceSwapResponse confirmResidenceSwapPeriodEligibility(@Valid StudentRequestByStudentIdDto studentRequestByStudentIdDto) {
        ConfirmResidenceSwapResponse confirmResidenceSwapResponse = new ConfirmResidenceSwapResponse();
        JSONObject currentTermObject = UtilityClass.getTermDetailsByDate(UtilityClass.getToday());
        JSONObject currentWeekObject = UtilityClass.getTheCurrentWeek(UtilityClass.getToday());

        if(currentTermObject == null) {
            confirmResidenceSwapResponse.setPeriodEligible(false);
            confirmResidenceSwapResponse.setEligibilityMessage("The change residence module does not work over the holidays. Kindly wait till the begining of the next term");
        } else if(currentTermObject != null && currentWeekObject != null && currentWeekObject.getInt("WeekIterationCode") > 5) {
            confirmResidenceSwapResponse.setPeriodEligible(false);
            confirmResidenceSwapResponse.setEligibilityMessage("You cannot change a student's residence more than 5 weeks into the term");
        } else {
            JSONObject studentResidenceDetails = UtilityClass.getAStudentResidenceDetails(studentRequestByStudentIdDto.getStudentId());
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
            for (int i = 0;i < currentFeeStructureArray.length();i++) {
                if (currentFeeStructureArray.getJSONObject(i).getInt("TermIterationId") == currentTermObject.getInt("TermIterationId")) {
                    currentTermFeeObject = currentFeeStructureArray.getJSONObject(i);
                }
            }

            currentTermFee = (double) currentTermFeeObject.getInt("FeeAmount");


            JSONArray proposedFeeStructureArray = UtilityClass.getFeeStructureForParticularClassLevel(classDetails.getInt("AcademicClassLevelId"), studentResidenceRepository.findByStudentResidenceCode(confirmResidenceSwapResponse.getProposedResidenceCode()).get(0).getStudentResidenceId());
            JSONObject proposedTermFeeObject = null;
            for (int i = 0;i < proposedFeeStructureArray.length();i++) {
                if (proposedFeeStructureArray.getJSONObject(i).getInt("TermIterationId") == currentTermObject.getInt("TermIterationId")) {
                    proposedTermFeeObject = proposedFeeStructureArray.getJSONObject(i);
                }
            }

            replacementTermFee = (double) proposedTermFeeObject.getInt("FeeAmount");


            if(confirmResidenceSwapResponse.getProposedResidenceCode() == 1) {
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
}
