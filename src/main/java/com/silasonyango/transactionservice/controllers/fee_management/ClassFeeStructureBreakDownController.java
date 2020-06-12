package com.silasonyango.transactionservice.controllers.fee_management;

import com.silasonyango.transactionservice.dtos.api_response.SuccessFailureResponseDto;
import com.silasonyango.transactionservice.entity_classes.fee_management.ClassFeeStructureBreakDownEntity;
import com.silasonyango.transactionservice.repository.fee_management.ClassFeeStructureBreakDownRepository;
import com.silasonyango.transactionservice.repository.student_management.StudentResidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/class_fee_structure_breakdown")
public class ClassFeeStructureBreakDownController {
    @Autowired
    ClassFeeStructureBreakDownRepository classFeeStructureBreakDownRepository;

    @Autowired
    StudentResidenceRepository studentResidenceRepository;

    @PostMapping("/create_fee_structure_breakdown")
    public SuccessFailureResponseDto createFeeStructureBreakDown(@Valid ClassFeeStructureBreakDownEntity classFeeStructureBreakDownEntity) {
        classFeeStructureBreakDownEntity.setStudentResidenceId(studentResidenceRepository.findByStudentResidenceCode(classFeeStructureBreakDownEntity.getStudentResidenceId()).get(0).getStudentResidenceId());

        classFeeStructureBreakDownRepository.save(classFeeStructureBreakDownEntity);

        return new SuccessFailureResponseDto(true,"Class fee structure breakdown created successfully","N/A");
    }
}
