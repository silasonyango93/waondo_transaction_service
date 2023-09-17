package com.silasonyango.transactionservice.repository.academic_classes;

import com.silasonyango.transactionservice.entity_classes.academic_classes.LotDescriptionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LotDescriptionsRepository extends JpaRepository<LotDescriptionsEntity, Long> {
    @Query("SELECT ld FROM LotDescriptionsEntity ld " +
            "JOIN ld.lotsEntities lts " +
            "JOIN lts.classesEntities cls " +
            "JOIN cls.studentEntities stds " +
            "JOIN stds.feeStatementEntities fsts " +
            "WHERE cls.classId = (:classId)")
    public LotDescriptionsEntity fetchStudentFeeBalancesPerClassStream(@Param("classId") int classId);
}
