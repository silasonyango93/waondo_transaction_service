package com.silasonyango.transactionservice.repository.fee_management;

import com.silasonyango.transactionservice.entity_classes.fee_management.TransactionDescriptionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDescriptionsRepository extends JpaRepository<TransactionDescriptionsEntity, Long> {
}
