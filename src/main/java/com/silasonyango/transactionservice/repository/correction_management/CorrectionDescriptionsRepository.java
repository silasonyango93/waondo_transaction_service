package com.silasonyango.transactionservice.repository.correction_management;

import com.silasonyango.transactionservice.entity_classes.system_initialization.correction_descriptions.CorrectionDescriptionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CorrectionDescriptionsRepository extends JpaRepository<CorrectionDescriptionsEntity, Long> {
    public CorrectionDescriptionsEntity findByCorrectionDescriptionCode(@Param("CorrectionDescriptionCode") int correctionDescriptionCode);
}
