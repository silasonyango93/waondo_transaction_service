package com.silasonyango.transactionservice.controllers.student_management;

import com.silasonyango.transactionservice.repository.student_management.StudentRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student_registration")
public class StudentRegistrationController {
    @Autowired
    StudentRegistrationRepository studentRegistrationRepository;
}
