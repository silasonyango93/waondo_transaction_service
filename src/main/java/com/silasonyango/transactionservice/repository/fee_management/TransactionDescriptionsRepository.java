package com.silasonyango.transactionservice.repository.fee_management;

import com.silasonyango.transactionservice.entity_classes.fee_management.TransactionDescriptionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionDescriptionsRepository extends JpaRepository<TransactionDescriptionsEntity, Long> {
    public List<TransactionDescriptionsEntity> findByTransactionDescriptionCode(@Param("TransactionDescriptionCode") int transactionDescriptionCode);
}
