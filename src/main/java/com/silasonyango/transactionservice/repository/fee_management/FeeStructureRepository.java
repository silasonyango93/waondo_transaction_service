package com.silasonyango.transactionservice.repository.fee_management;

import com.silasonyango.transactionservice.entity_classes.fee_management.FeeStructureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface FeeStructureRepository extends JpaRepository<FeeStructureEntity, Long> {
    public FeeStructureEntity findByFeeStructureId(@Param("FeeStructureId") int feeStructureId);
}
