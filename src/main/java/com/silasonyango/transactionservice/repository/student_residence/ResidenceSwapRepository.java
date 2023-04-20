package com.silasonyango.transactionservice.repository.student_residence;

import com.silasonyango.transactionservice.entity_classes.student_residence.ResidenceSwapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ResidenceSwapRepository extends JpaRepository<ResidenceSwapEntity, Long> {
    void deleteByStudentId(@Param("StudentId") int studentId);
}
