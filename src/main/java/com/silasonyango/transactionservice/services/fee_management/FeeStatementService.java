package com.silasonyango.transactionservice.services.fee_management;

import com.silasonyango.transactionservice.controllers.fee_management.InstallmentsController;
import com.silasonyango.transactionservice.dtos.fee_management.FeeStatementResponseDto;
import com.silasonyango.transactionservice.entity_classes.fee_management.FeeStatementEntity;
import com.silasonyango.transactionservice.repository.academic_classes.ClassesRepository;
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

    public List<Map<String, Object>> fetchStudentFeeBalancesPerClassStream(int classId) {
        String sql = String.format("SELECT * FROM lot_descriptions INNER JOIN lots ON lot_descriptions.LotDescriptionId " +
                "= lots.LotDescriptionId INNER JOIN academic_class_levels ON academic_class_levels.AcademicClassLevelId " +
                "= lots.AcademicClassLevelId INNER JOIN classes ON lots.LotId = classes.LotId INNER JOIN class_streams " +
                "ON classes.ClassStreamId = class_streams.ClassStreamId INNER JOIN students ON students.ClassId " +
                "= classes.ClassId INNER JOIN fee_statements ON fee_statements.StudentId = students.StudentId " +
                "WHERE classes.ClassId = %s;", classId);
        return jdbcTemplate.queryForList(sql);
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
                "= classes.ClassId INNER JOIN gender ON students.GenderId = gender.GenderId INNER JOIN student_residence " +
                "ON students.StudentResidenceId = student_residence.StudentResidenceId INNER JOIN fee_statements " +
                "ON fee_statements.StudentId = students.StudentId " +
                "WHERE lots.LotId = %s AND fee_statements.CurrentTermBalance >= %s;", lotId, thresholdTermBalance);
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> fetchFeeBalancesForEntireSchoolWithTermBalanceGreaterThanOrEqualProvidedAmount(
            int thresholdTermBalance) {
        String sql = String.format("SELECT * FROM lot_descriptions INNER JOIN lots ON lot_descriptions.LotDescriptionId " +
                "= lots.LotDescriptionId INNER JOIN academic_class_levels ON academic_class_levels.AcademicClassLevelId " +
                "= lots.AcademicClassLevelId INNER JOIN classes ON lots.LotId = classes.LotId INNER JOIN class_streams " +
                "ON classes.ClassStreamId = class_streams.ClassStreamId INNER JOIN students ON students.ClassId " +
                "= classes.ClassId INNER JOIN gender ON students.GenderId = gender.GenderId INNER JOIN student_residence " +
                "ON students.StudentResidenceId = student_residence.StudentResidenceId INNER JOIN fee_statements " +
                "ON fee_statements.StudentId = students.StudentId " +
                "WHERE lots.hasCompletedSchool = 0 AND academic_class_levels.IsAdminClassLevel = 0 " +
                "AND fee_statements.CurrentTermBalance >= %s;", thresholdTermBalance);
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> fetchFeeBalancesForASpecificClassWithTermBalanceGreaterThanOrEqualProvidedAmount(
            int classId, int thresholdTermBalance) {
        String sql = String.format("SELECT * FROM lot_descriptions INNER JOIN lots ON lot_descriptions.LotDescriptionId " +
                "= lots.LotDescriptionId INNER JOIN academic_class_levels ON academic_class_levels.AcademicClassLevelId " +
                "= lots.AcademicClassLevelId INNER JOIN classes ON lots.LotId = classes.LotId INNER JOIN class_streams " +
                "ON classes.ClassStreamId = class_streams.ClassStreamId INNER JOIN students ON students.ClassId " +
                "= classes.ClassId INNER JOIN gender ON students.GenderId = gender.GenderId INNER JOIN student_residence " +
                "ON students.StudentResidenceId = student_residence.StudentResidenceId INNER JOIN fee_statements " +
                "ON fee_statements.StudentId = students.StudentId " +
                "WHERE classes.ClassId = %s AND fee_statements.CurrentTermBalance >= %s;", classId, thresholdTermBalance);
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> fetchFeeBalancesForASpecificListOfStudents(List<Integer> studentIds) {
        String sql = String.format("SELECT * FROM lot_descriptions INNER JOIN lots ON lot_descriptions.LotDescriptionId " +
                "= lots.LotDescriptionId INNER JOIN academic_class_levels ON academic_class_levels.AcademicClassLevelId " +
                "= lots.AcademicClassLevelId INNER JOIN classes ON lots.LotId = classes.LotId INNER JOIN class_streams " +
                "ON classes.ClassStreamId = class_streams.ClassStreamId INNER JOIN students ON students.ClassId " +
                "= classes.ClassId INNER JOIN fee_statements ON fee_statements.StudentId = students.StudentId INNER JOIN gender ON students.GenderId = gender.GenderId " +
                "WHERE students.StudentId IN %s", returnProcessedDynamicInClause(studentIds));
        return jdbcTemplate.queryForList(sql);
    }

    public String returnProcessedDynamicInClause(List<Integer> referenceList) {
        String result = "(";
        for (int i = 0; i < referenceList.size(); i++) {
            if (i != referenceList.size() - 1) {
                result = String.format("%s'%s',", result, referenceList.get(i));
            } else {
                result = String.format("%s'%s');", result, referenceList.get(i));
            }
        }
        return result;
    }
}
