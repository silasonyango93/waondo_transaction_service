package com.silasonyango.transactionservice.repository.student_management;

import com.silasonyango.transactionservice.entity_classes.student_management.StudentResidenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentResidenceRepository extends JpaRepository<StudentResidenceEntity, Long> {
    public List<StudentResidenceEntity> findByStudentResidenceCode(@Param("StudentResidenceCode") int studentResidenceCode);

    public StudentResidenceEntity findByStudentResidenceId(@Param("StudentResidenceId") int studentResidenceId);
}
