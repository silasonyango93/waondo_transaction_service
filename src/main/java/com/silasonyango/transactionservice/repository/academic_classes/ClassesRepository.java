package com.silasonyango.transactionservice.repository.academic_classes;

import com.silasonyango.transactionservice.entity_classes.academic_classes.ClassesEntity;
import com.silasonyango.transactionservice.entity_classes.academic_classes.LotDescriptionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClassesRepository extends JpaRepository<ClassesEntity, Long> {
    @Query("SELECT cls FROM ClassesEntity cls " +
            "JOIN cls.studentEntities stds " +
            "JOIN stds.feeStatementEntities fsts " +
            "WHERE cls.classId = (:classId)")
    public ClassesEntity fetchStudentFeeBalancesPerClassStream(@Param("classId") int classId);
}
