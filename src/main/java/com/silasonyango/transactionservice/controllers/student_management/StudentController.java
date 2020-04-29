package com.silasonyango.transactionservice.controllers.student_management;

import com.silasonyango.transactionservice.common.config.SessionActivitiesConfig;
import com.silasonyango.transactionservice.dtos.api_response.SuccessFailureResponseDto;
import com.silasonyango.transactionservice.dtos.student_management.StudentRegistrationDto;
import com.silasonyango.transactionservice.entity_classes.fee_management.FeeStatementEntity;
import com.silasonyango.transactionservice.entity_classes.session_management.UserSessionActivitiesEntity;
import com.silasonyango.transactionservice.entity_classes.student_management.StudentEntity;
import com.silasonyango.transactionservice.repository.fee_management.FeeStatementRepository;
import com.silasonyango.transactionservice.repository.session_management.UserSessionActivitiesRepository;
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

    @Autowired
    UserSessionActivitiesRepository userSessionActivitiesRepository;

    @PostMapping("/create_student")
    public SuccessFailureResponseDto createAStudent(@Valid StudentRegistrationDto studentRegistrationDto) {
        SuccessFailureResponseDto successFailureResponseDto = new SuccessFailureResponseDto();

        List<StudentEntity> existingStudentsArrayList = studentRepository.findByAdmissionNo(studentRegistrationDto.getAdmissionNo());

        if(existingStudentsArrayList.size() > 0) {
            successFailureResponseDto.setSuccessStatus(false);
            successFailureResponseDto.setResponseMessage("A student already exists by this admission number");
            successFailureResponseDto.setReturnValue("N/A");
        } else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            StudentEntity student = new StudentEntity();
            student.setAdmissionNo(studentRegistrationDto.getAdmissionNo());
            student.setStudentName(studentRegistrationDto.getStudentName());
            student.setGenderId(studentRegistrationDto.getGenderId());
            student.setClassId(studentRegistrationDto.getClassId());
            student.setStudentDob(studentRegistrationDto.getStudentDob());
            student.setProfPicName(studentRegistrationDto.getProfPicName());
            student.setStudentTypeId(studentRegistrationDto.getStudentTypeId());
            student.setAdmissionDate(dtf.format(now));

            StudentEntity dbSavedStudent = studentRepository.save(student);

            createAFeeStatement(dbSavedStudent.getStudentId());

            userSessionActivitiesRepository.save(new UserSessionActivitiesEntity(studentRegistrationDto.getRegistrationSessionId(), SessionActivitiesConfig.REGISTER_A_STUDENT_SESSION_ACTIVITY, dtf.format(now)));

            successFailureResponseDto.setSuccessStatus(true);
            successFailureResponseDto.setResponseMessage("Student successfully registered");
            successFailureResponseDto.setReturnValue("N/A");
        }



        return successFailureResponseDto;
    }

    public FeeStatementEntity createAFeeStatement(int studentId) {

        return feeStatementRepository.save(new FeeStatementEntity(studentId,0,0,0,0,0));

    }
}
