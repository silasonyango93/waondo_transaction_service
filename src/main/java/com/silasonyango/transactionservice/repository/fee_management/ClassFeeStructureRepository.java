package com.silasonyango.transactionservice.repository.fee_management;

import com.silasonyango.transactionservice.entity_classes.fee_management.ClassFeeStructuresEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassFeeStructureRepository extends JpaRepository<ClassFeeStructuresEntity, Long> {
}
