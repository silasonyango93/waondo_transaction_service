package com.silasonyango.transactionservice.services.student;

import com.silasonyango.transactionservice.repository.student_management.StudentRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentRegistrationService {

    @Autowired
    StudentRegistrationRepository studentRegistrationRepository;

    public boolean deleteStudentRegistrationByStudentId(int studentId) {
        try {
            studentRegistrationRepository.deleteByStudentId(studentId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
