package com.silasonyango.transactionservice.repository.system_initialization.correction_descriptions;

import com.silasonyango.transactionservice.entity_classes.system_initialization.correction_descriptions.CorrectionDescriptionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CorrectionDescriptionsRepository extends JpaRepository<CorrectionDescriptionsEntity, Long> {
    public List<CorrectionDescriptionsEntity> findByCorrectionDescriptionCode(@Param("CorrectionDescriptionCode") int correctionDescriptionCode);
}
