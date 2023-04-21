package com.silasonyango.transactionservice.services.fee_statement;

import com.silasonyango.transactionservice.repository.fee_management.StudentFeeComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentFeeComponentService {

    @Autowired
    StudentFeeComponentRepository studentFeeComponentRepository;

    public boolean deleteStudentFeeComponentByStudentId(int studentId) {
        try {
            studentFeeComponentRepository.deleteByStudentId(studentId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
