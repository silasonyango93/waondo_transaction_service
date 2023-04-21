package com.silasonyango.transactionservice.services.student;

import com.silasonyango.transactionservice.repository.fee_management.*;
import com.silasonyango.transactionservice.repository.student_management.StudentRegistrationRepository;
import com.silasonyango.transactionservice.repository.student_management.StudentRepository;
import com.silasonyango.transactionservice.repository.student_residence.ResidenceSwapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentsService {

    @Autowired
    TransactionsRepository transactionsRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ResidenceSwapRepository residenceSwapRepository;

    @Autowired
    StudentRegistrationRepository studentRegistrationRepository;

    @Autowired
    StudentFeeComponentRepository studentFeeComponentRepository;

    @Autowired
    FeeStatementRepository feeStatementRepository;

    @Autowired
    FeeCorrectionsRepository feeCorrectionsRepository;

    @Autowired
    CarryForwardsRepository carryForwardsRepository;

    public boolean deleteStudentByStudentId(int studentId) {
        try {
            if (studentRepository.findByStudentId(studentId).isEmpty()) {
                return false;
            }
            if (!transactionsRepository.findByStudentId(studentId).isEmpty()) {
                transactionsRepository.deleteByStudentId(studentId);
            }
            if (!residenceSwapRepository.findByStudentId(studentId).isEmpty()) {
                residenceSwapRepository.deleteByStudentId(studentId);
            }
            if (!studentRegistrationRepository.findByStudentId(studentId).isEmpty()) {
                studentRegistrationRepository.deleteByStudentId(studentId);
            }
            if (!studentFeeComponentRepository.findByStudentId(studentId).isEmpty()) {
                studentFeeComponentRepository.deleteByStudentId(studentId);
            }
            if (!feeStatementRepository.findByStudentId(studentId).isEmpty()) {
                feeStatementRepository.deleteByStudentId(studentId);
            }
            if (!feeCorrectionsRepository.findByStudentId(studentId).isEmpty()) {
                feeCorrectionsRepository.deleteByStudentId(studentId);
            }
            if (!carryForwardsRepository.findByStudentId(studentId).isEmpty()) {
                carryForwardsRepository.deleteByStudentId(studentId);
            }
            studentRepository.deleteByStudentId(studentId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
