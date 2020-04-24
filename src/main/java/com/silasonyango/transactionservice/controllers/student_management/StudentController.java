package com.silasonyango.transactionservice.controllers.student_management;

import com.silasonyango.transactionservice.dtos.api_response.SuccessFailureResponseDto;
import com.silasonyango.transactionservice.entity_classes.fee_management.FeeStatementEntity;
import com.silasonyango.transactionservice.entity_classes.student_management.StudentEntity;
import com.silasonyango.transactionservice.repository.fee_management.FeeStatementRepository;
import com.silasonyango.transactionservice.repository.student_management.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    FeeStatementRepository feeStatementRepository;

    @PostMapping("/create_student")
    public SuccessFailureResponseDto createAStudent(@Valid StudentEntity studentEntity) {
        SuccessFailureResponseDto successFailureResponseDto = new SuccessFailureResponseDto();

        List<StudentEntity> existingStudentsArrayList = studentRepository.findByAdmissionNo(studentEntity.getAdmissionNo());

        if(existingStudentsArrayList.size() > 0) {
            successFailureResponseDto.setSuccessStatus(false);
            successFailureResponseDto.setResponseMessage("A student already exists by this admission number");
            successFailureResponseDto.setReturnValue("N/A");
        } else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            studentEntity.setAdmissionDate(dtf.format(now));

            StudentEntity dbSavedStudent = studentRepository.save(studentEntity);

            createAFeeStatement(dbSavedStudent.getAdmissionNo());

            successFailureResponseDto.setSuccessStatus(true);
            successFailureResponseDto.setResponseMessage("Student successfully registered");
            successFailureResponseDto.setReturnValue("N/A");
        }



        return successFailureResponseDto;
    }

    public FeeStatementEntity createAFeeStatement(String admissionNumber) {

        return feeStatementRepository.save(new FeeStatementEntity(admissionNumber,0,0,0,0,0,0,0,0,0,0));

    }
}
