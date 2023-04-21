package com.silasonyango.transactionservice.repository.fee_management;

import com.silasonyango.transactionservice.entity_classes.fee_management.StudentFeeComponentEntity;
import com.silasonyango.transactionservice.entity_classes.student_management.StudentRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentFeeComponentRepository extends JpaRepository<StudentFeeComponentEntity, Long> {
    void deleteByStudentId(@Param("StudentId") int studentId);

    public List<StudentFeeComponentEntity> findByStudentId(@Param("StudentId") int studentId);
}
