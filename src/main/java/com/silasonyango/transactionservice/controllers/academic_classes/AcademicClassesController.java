package com.silasonyango.transactionservice.controllers.academic_classes;

import com.silasonyango.transactionservice.services.academic_classes.AcademicClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/academic-classes")
public class AcademicClassesController {

    @Autowired
    AcademicClassesService academicClassesService;

    @GetMapping("/class-details/fetch-class-by-its-full-name")
    public ResponseEntity<Map<String, Object>> fetchClassByItsFullName(@RequestParam("classId") int classId) {
        try {
            return new ResponseEntity<Map<String, Object>>(academicClassesService.fetchClassByItsFullName(classId)
                    , HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<Map<String, Object>>(HttpStatus.valueOf(400));
        }
    }

    @GetMapping("/lots/fetch-all-lots-currently-not-completed-school-full-details")
    public ResponseEntity<List<Map<String, Object>>> fetchAllLotsCurrentlyNotCompletedSchoolByFullDetails() {
        try {
            return new ResponseEntity<List<Map<String, Object>>>(academicClassesService
                    .fetchAllLotsCurrentlyNotCompletedSchoolByFullDetails()
                    , HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<List<Map<String, Object>>>(HttpStatus.valueOf(400));
        }
    }

    @GetMapping("/actual-classes/fetch-all-actual-classes-currently-not-completed-school-full-details")
    public ResponseEntity<List<Map<String, Object>>> fetchAllActualClassesCurrentlyNotCompletedSchoolByFullDetails() {
        try {
            return new ResponseEntity<List<Map<String, Object>>>(academicClassesService
                    .fetchAllActualClassesCurrentlyNotCompletedSchoolByFullDetails()
                    , HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<List<Map<String, Object>>>(HttpStatus.valueOf(400));
        }
    }

}
