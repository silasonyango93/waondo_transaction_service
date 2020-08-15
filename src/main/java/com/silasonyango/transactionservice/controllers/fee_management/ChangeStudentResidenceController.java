package com.silasonyango.transactionservice.controllers.fee_management;

import com.silasonyango.transactionservice.dtos.student_management.StudentRequestByStudentIdDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/residence-swap")
public class ChangeStudentResidenceController {

    @PostMapping("/confirm_residence_swap")
    public void confirmResidenceSwap(@Valid StudentRequestByStudentIdDto studentRequestByStudentIdDto) {

    }
}
