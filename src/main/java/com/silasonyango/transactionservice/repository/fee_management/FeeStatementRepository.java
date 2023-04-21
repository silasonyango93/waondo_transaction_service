package com.silasonyango.transactionservice.repository.fee_management;

import com.silasonyango.transactionservice.entity_classes.fee_management.FeeStatementEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.StudentFeeComponentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeeStatementRepository extends JpaRepository<FeeStatementEntity, Long> {
    public List<FeeStatementEntity> findFeeStatementByStudentId(@Param("StudentId") int studentId);

    void deleteByStudentId(@Param("StudentId") int studentId);

    public List<FeeStatementEntity> findByStudentId(@Param("StudentId") int studentId);
}
