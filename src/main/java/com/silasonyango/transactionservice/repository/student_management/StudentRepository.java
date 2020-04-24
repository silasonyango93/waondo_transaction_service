package com.silasonyango.transactionservice.repository.student_management;

import com.silasonyango.transactionservice.entity_classes.student_management.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    public List<StudentEntity> findByAdmissionNo(@Param("AdmissionNo") String admissionNo);
}
