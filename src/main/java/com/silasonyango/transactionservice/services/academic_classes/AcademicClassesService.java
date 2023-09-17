package com.silasonyango.transactionservice.services.academic_classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
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
}
