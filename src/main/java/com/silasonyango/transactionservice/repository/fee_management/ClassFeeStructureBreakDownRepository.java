package com.silasonyango.transactionservice.repository.fee_management;

import com.silasonyango.transactionservice.entity_classes.fee_management.ClassFeeStructureBreakDownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ClassFeeStructureBreakDownRepository extends JpaRepository<ClassFeeStructureBreakDownEntity, Long> {
    public ClassFeeStructureBreakDownEntity findByAcademicLevelFeeStructureBreakDownId(@Param("ClassFeeStructureBreakDownId") int academicLevelFeeStructureBreakDownId);
}
