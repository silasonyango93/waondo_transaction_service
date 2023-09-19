package com.silasonyango.transactionservice.services.fee_management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FeeInstallmentsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> fetchFeeInstallmentsMadeToday() {
        String sql = String.format("SELECT * FROM lot_descriptions INNER JOIN lots ON lot_descriptions.LotDescriptionId " +
                "= lots.LotDescriptionId INNER JOIN academic_class_levels ON academic_class_levels.AcademicClassLevelId " +
                "= lots.AcademicClassLevelId INNER JOIN classes ON lots.LotId = classes.LotId INNER JOIN class_streams " +
                "ON classes.ClassStreamId = class_streams.ClassStreamId INNER JOIN students ON students.ClassId " +
                "= classes.ClassId INNER JOIN fee_statements ON fee_statements.StudentId = students.StudentId " +
                "INNER JOIN installments ON installments.StudentId = students.StudentId " +
                "WHERE DATE(installments.InstallmentDate) = CURDATE();");
        return jdbcTemplate.queryForList(sql);
    }
}
