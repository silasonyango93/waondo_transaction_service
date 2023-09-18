package com.silasonyango.transactionservice.services.fee_statement;

import com.silasonyango.transactionservice.controllers.fee_management.InstallmentsController;
import com.silasonyango.transactionservice.dtos.fee_management.FeeStatementResponseDto;
import com.silasonyango.transactionservice.entity_classes.academic_classes.ClassesEntity;
import com.silasonyango.transactionservice.entity_classes.academic_classes.LotDescriptionsEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.FeeStatementEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.InstallmentsEntity;
import com.silasonyango.transactionservice.entity_classes.student_management.StudentEntity;
import com.silasonyango.transactionservice.repository.academic_classes.ClassesRepository;
import com.silasonyango.transactionservice.repository.academic_classes.LotDescriptionsRepository;
import com.silasonyango.transactionservice.repository.fee_management.FeeStatementRepository;
import com.silasonyango.transactionservice.repository.fee_management.InstallmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FeeStatementService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    FeeStatementRepository feeStatementRepository;

    @Autowired
    InstallmentRepository installmentRepository;

    @Autowired
    InstallmentsController installmentsController;

    @Autowired
    ClassesRepository classesRepository;

    public FeeStatementEntity getAStudentFeeStatement(int studentId) {
        return feeStatementRepository.findFeeStatementByStudentId(studentId).get(0);
    }

    public FeeStatementResponseDto getAStudentFeeStatementSinceJoining(int studentId) {
        return installmentsController.getAStudentFeeStatementSinceJoining(studentId);
    }

    public FeeStatementResponseDto getAStudentFeeStatementForCurrentYear(int studentId) {
        return installmentsController.getAStudentFeeStatementForCurrentYear(studentId);
    }

    public boolean deleteFeeStatementsByStudentId(int studentId) {
        try {
            feeStatementRepository.deleteByStudentId(studentId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ClassesEntity fetchStudentFeeBalancesPerClassStream(int classId) {
        return classesRepository
                .fetchStudentFeeBalancesPerClassStream(classId);
    }

    public List<Map<String, Object>> fetchStudentFeeBalancesForASpecificLot(int lotId) {
        String sql = String.format("SELECT * FROM lot_descriptions INNER JOIN lots ON lot_descriptions.LotDescriptionId " +
                "= lots.LotDescriptionId INNER JOIN academic_class_levels ON academic_class_levels.AcademicClassLevelId " +
                "= lots.AcademicClassLevelId INNER JOIN classes ON lots.LotId = classes.LotId INNER JOIN class_streams " +
                "ON classes.ClassStreamId = class_streams.ClassStreamId INNER JOIN students ON students.ClassId " +
                "= classes.ClassId INNER JOIN fee_statements ON fee_statements.StudentId = students.StudentId " +
                "WHERE lots.LotId = %s;", lotId);
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> fetchFeeBalancesForASpecificLotWithTermBalanceGreaterThanOrEqualProvidedAmount(
            int lotId, int thresholdTermBalance) {
        String sql = String.format("SELECT * FROM lot_descriptions INNER JOIN lots ON lot_descriptions.LotDescriptionId " +
                "= lots.LotDescriptionId INNER JOIN academic_class_levels ON academic_class_levels.AcademicClassLevelId " +
                "= lots.AcademicClassLevelId INNER JOIN classes ON lots.LotId = classes.LotId INNER JOIN class_streams " +
                "ON classes.ClassStreamId = class_streams.ClassStreamId INNER JOIN students ON students.ClassId " +
                "= classes.ClassId INNER JOIN fee_statements ON fee_statements.StudentId = students.StudentId " +
                "WHERE lots.LotId = %s AND fee_statements.CurrentTermBalance >= %s;", lotId, thresholdTermBalance);
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> fetchFeeBalancesForASpecificClassWithTermBalanceGreaterThanOrEqualProvidedAmount(
            int classId, int thresholdTermBalance) {
        String sql = String.format("SELECT * FROM lot_descriptions INNER JOIN lots ON lot_descriptions.LotDescriptionId " +
                "= lots.LotDescriptionId INNER JOIN academic_class_levels ON academic_class_levels.AcademicClassLevelId " +
                "= lots.AcademicClassLevelId INNER JOIN classes ON lots.LotId = classes.LotId INNER JOIN class_streams " +
                "ON classes.ClassStreamId = class_streams.ClassStreamId INNER JOIN students ON students.ClassId " +
                "= classes.ClassId INNER JOIN fee_statements ON fee_statements.StudentId = students.StudentId " +
                "WHERE classes.ClassId = %s AND fee_statements.CurrentTermBalance >= %s;", classId, thresholdTermBalance);
        return jdbcTemplate.queryForList(sql);
    }
}
