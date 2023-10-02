package com.silasonyango.transactionservice.services.academic_classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AcademicClassesService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> fetchClassByItsFullName(int classId) {
        String sql = String.format("SELECT * FROM lot_descriptions INNER JOIN lots ON lot_descriptions.LotDescriptionId " +
                "= lots.LotDescriptionId INNER JOIN academic_class_levels ON academic_class_levels.AcademicClassLevelId " +
                "= lots.AcademicClassLevelId INNER JOIN classes ON lots.LotId = classes.LotId INNER JOIN class_streams " +
                "ON classes.ClassStreamId = class_streams.ClassStreamId WHERE classes.ClassId = %s;", classId);
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
        return results.get(0);
    }

    public Map<String, Object> fetchLotByItsFullName(int lotId) {
        String sql = String.format("SELECT * FROM lot_descriptions INNER JOIN lots ON lot_descriptions.LotDescriptionId " +
                "= lots.LotDescriptionId INNER JOIN academic_class_levels ON academic_class_levels.AcademicClassLevelId " +
                "= lots.AcademicClassLevelId WHERE lots.LotId = %s;", lotId);
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
        return results.get(0);
    }

    public List<Map<String, Object>> fetchAllLotsCurrentlyNotCompletedSchoolByFullDetails() {
        String sql = String.format("SELECT * FROM lots INNER JOIN academic_class_levels ON lots.AcademicClassLevelId " +
                "= academic_class_levels.AcademicClassLevelId INNER JOIN lot_descriptions ON lots.LotDescriptionId " +
                "= lot_descriptions.LotDescriptionId WHERE lots.hasCompletedSchool = 0 " +
                "AND academic_class_levels.IsAdminClassLevel = 0;");
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> fetchAllActualClassesCurrentlyNotCompletedSchoolByFullDetails() {
        String sql = String.format("SELECT * FROM classes INNER JOIN class_streams ON classes.ClassStreamId " +
                "= class_streams.ClassStreamId INNER JOIN lots ON classes.LotId = lots.LotId INNER JOIN lot_descriptions " +
                "ON lots.LotDescriptionId = lot_descriptions.LotDescriptionId INNER JOIN academic_class_levels " +
                "ON lots.AcademicClassLevelId = academic_class_levels.AcademicClassLevelId WHERE lots.hasCompletedSchool " +
                "= 0 AND academic_class_levels.IsAdminClassLevel = 0;");
        return jdbcTemplate.queryForList(sql);
    }
}
