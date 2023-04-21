package com.silasonyango.transactionservice.repository.student_management;

import com.silasonyango.transactionservice.entity_classes.student_management.StudentRegistrationEntity;
import com.silasonyango.transactionservice.entity_classes.student_residence.ResidenceSwapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRegistrationRepository extends JpaRepository<StudentRegistrationEntity, Long> {
    void deleteByStudentId(@Param("StudentId") int studentId);

    public List<StudentRegistrationEntity> findByStudentId(@Param("StudentId") int studentId);
}
