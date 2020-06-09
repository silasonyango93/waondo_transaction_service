package com.silasonyango.transactionservice.repository.fee_management;

import com.silasonyango.transactionservice.entity_classes.fee_management.FeeCorrectionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeeCorrectionsRepository extends JpaRepository<FeeCorrectionsEntity, Long> {
}
