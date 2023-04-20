package com.silasonyango.transactionservice.repository.fee_management;

import com.silasonyango.transactionservice.entity_classes.fee_management.TransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface TransactionsRepository extends JpaRepository<TransactionsEntity, Long> {
    void deleteByStudentId(@Param("StudentId") int studentId);
}
