package com.silasonyango.transactionservice.controllers.fee_management;

import com.silasonyango.transactionservice.dtos.student_management.StudentRequestByStudentIdDto;
import com.silasonyango.transactionservice.dtos.student_residence.ConfirmResidenceSwapResponse;
import com.silasonyango.transactionservice.utility_classes.UtilityClass;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/change_student_residence")
public class ChangeStudentResidenceController {

    @PostMapping("/confirm_residence_swap")
    public ConfirmResidenceSwapResponse confirmResidenceSwapPeriodEligibility(@Valid StudentRequestByStudentIdDto studentRequestByStudentIdDto) {
        ConfirmResidenceSwapResponse confirmResidenceSwapResponse = new ConfirmResidenceSwapResponse();
//        JSONObject currentTermObject = UtilityClass.getTermDetailsByDate(UtilityClass.getToday());
//        JSONObject currentWeekObject = UtilityClass.getTheCurrentWeek(UtilityClass.getToday());
        JSONObject currentTermObject = UtilityClass.getTermDetailsByDate("2020-09-15");
        JSONObject currentWeekObject = UtilityClass.getTheCurrentWeek("2020-09-15");

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

        }

        return confirmResidenceSwapResponse;
    }
}
