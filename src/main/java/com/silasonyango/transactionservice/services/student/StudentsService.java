package com.silasonyango.transactionservice.services.student;

import com.silasonyango.transactionservice.repository.student_management.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentsService {

    @Autowired
    StudentRepository studentRepository;

    public boolean deleteStudentByStudentId(int studentId) {
        try {
            studentRepository.deleteByStudentId(studentId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
