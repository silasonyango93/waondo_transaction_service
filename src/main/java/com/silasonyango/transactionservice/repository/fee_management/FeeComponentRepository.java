package com.silasonyango.transactionservice.repository.fee_management;

import com.silasonyango.transactionservice.entity_classes.fee_management.FeeComponentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeeComponentRepository extends JpaRepository<FeeComponentsEntity, Long> {
}
