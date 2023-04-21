package com.silasonyango.transactionservice.repository.fee_management;

import com.silasonyango.transactionservice.entity_classes.fee_management.FeeCorrectionsEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.FeeStatementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeeCorrectionsRepository extends JpaRepository<FeeCorrectionsEntity, Long> {
    public List<FeeCorrectionsEntity> findByIsAdminFeeCorrection(@Param("IsAdminFeeCorrection") int isAdminFeeCorrection);

    void deleteByStudentId(@Param("StudentId") int studentId);

    public List<FeeCorrectionsEntity> findByStudentId(@Param("StudentId") int studentId);
}
