package com.silasonyango.transactionservice.controllers.academic_classes;

import com.silasonyango.transactionservice.repository.academic_classes.AcademicClassLevelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/academic_class_levels")
public class AcademicClassLevelsController {
    @Autowired
    AcademicClassLevelsRepository academicClassLevelsRepository;
}
