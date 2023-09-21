package com.silasonyango.transactionservice.services.student;

import com.silasonyango.transactionservice.repository.fee_management.*;
import com.silasonyango.transactionservice.repository.student_management.StudentRegistrationRepository;
import com.silasonyango.transactionservice.repository.student_management.StudentRepository;
import com.silasonyango.transactionservice.repository.student_residence.ResidenceSwapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StudentsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    @Autowired
    InstallmentRepository installmentRepository;

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
            if (!installmentRepository.findInstallmentsByStudentId(studentId).isEmpty()) {
                installmentRepository.deleteByStudentId(studentId);
            }
            studentRepository.deleteByStudentId(studentId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Map<String, Object>> fetchStudentsOfAParticularLot(int lotId) {
        String sql = String.format("SELECT * FROM lot_descriptions INNER JOIN lots ON lot_descriptions.LotDescriptionId " +
                "= lots.LotDescriptionId INNER JOIN academic_class_levels ON academic_class_levels.AcademicClassLevelId " +
                "= lots.AcademicClassLevelId INNER JOIN classes ON lots.LotId = classes.LotId INNER JOIN class_streams " +
                "ON classes.ClassStreamId = class_streams.ClassStreamId INNER JOIN students ON students.ClassId " +
                "= classes.ClassId INNER JOIN gender ON students.GenderId = gender.GenderId WHERE lots.LotId = %s;", lotId);
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> fetchStudentsOfAParticularClassStream(int classId) {
        String sql = String.format("SELECT * FROM lot_descriptions INNER JOIN lots ON lot_descriptions.LotDescriptionId " +
                "= lots.LotDescriptionId INNER JOIN academic_class_levels ON academic_class_levels.AcademicClassLevelId " +
                "= lots.AcademicClassLevelId INNER JOIN classes ON lots.LotId = classes.LotId INNER JOIN class_streams " +
                "ON classes.ClassStreamId = class_streams.ClassStreamId INNER JOIN students ON students.ClassId " +
                "= classes.ClassId INNER JOIN gender ON students.GenderId = gender.GenderId WHERE classes.ClassId = %s;", classId);
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> fetchAllStudentsInEntireSchoolNotCompletedSchool() {
        String sql = String.format("SELECT * FROM lot_descriptions INNER JOIN lots ON lot_descriptions.LotDescriptionId " +
                "= lots.LotDescriptionId INNER JOIN academic_class_levels ON academic_class_levels.AcademicClassLevelId " +
                "= lots.AcademicClassLevelId INNER JOIN classes ON lots.LotId = classes.LotId INNER JOIN class_streams " +
                "ON classes.ClassStreamId = class_streams.ClassStreamId INNER JOIN students ON students.ClassId " +
                "= classes.ClassId INNER JOIN gender ON students.GenderId = gender.GenderId " +
                "WHERE lots.hasCompletedSchool = 0;");
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> fetchAllStudentsInEntireSchoolWhoHaveCompletedSchool() {
        String sql = String.format("SELECT * FROM lot_descriptions INNER JOIN lots ON lot_descriptions.LotDescriptionId " +
                "= lots.LotDescriptionId INNER JOIN academic_class_levels ON academic_class_levels.AcademicClassLevelId " +
                "= lots.AcademicClassLevelId INNER JOIN classes ON lots.LotId = classes.LotId INNER JOIN class_streams " +
                "ON classes.ClassStreamId = class_streams.ClassStreamId INNER JOIN students ON students.ClassId " +
                "= classes.ClassId INNER JOIN gender ON students.GenderId = gender.GenderId " +
                "WHERE lots.hasCompletedSchool = 1;");
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> fetchStudentsFromAProvidedListOfStudentIds(List<Integer> studentIds) {
        String sql = String.format("SELECT * FROM lot_descriptions INNER JOIN lots ON lot_descriptions.LotDescriptionId " +
                "= lots.LotDescriptionId INNER JOIN academic_class_levels ON academic_class_levels.AcademicClassLevelId " +
                "= lots.AcademicClassLevelId INNER JOIN classes ON lots.LotId = classes.LotId INNER JOIN class_streams " +
                "ON classes.ClassStreamId = class_streams.ClassStreamId INNER JOIN students ON students.ClassId " +
                "= classes.ClassId INNER JOIN gender ON students.GenderId = gender.GenderId " +
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
