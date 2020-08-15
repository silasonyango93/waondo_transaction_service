package com.silasonyango.transactionservice.controllers.fee_management;

import com.silasonyango.transactionservice.dtos.student_management.StudentRequestByStudentIdDto;
import com.silasonyango.transactionservice.utility_classes.UtilityClass;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/change_student_residence")
public class ChangeStudentResidenceController {

    @PostMapping("/confirm_residence_swap_period_eligibility")
    public boolean confirmResidenceSwapPeriodEligibility(@Valid StudentRequestByStudentIdDto studentRequestByStudentIdDto) {
        JSONObject currentWeekObject = UtilityClass.getTheCurrentWeek(UtilityClass.getToday());
        return currentWeekObject.getInt("WeekIterationCode") < 5;
    }
}
