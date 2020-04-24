package com.silasonyango.transactionservice.repository.fee_management;

import com.silasonyango.transactionservice.entity_classes.fee_management.FeeStatementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeeStatementRepository extends JpaRepository<FeeStatementEntity, Long> {
}
