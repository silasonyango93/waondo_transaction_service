package com.silasonyango.transactionservice.repository.fee_management;

import com.silasonyango.transactionservice.entity_classes.fee_management.CarryForwardsEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.FeeCorrectionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarryForwardsRepository extends JpaRepository<CarryForwardsEntity, Long> {
    public List<CarryForwardsEntity> findByIsAdminCarryForward(@Param("IsAdminCarryForward") int isAdminCarryForward);

    void deleteByStudentId(@Param("StudentId") int studentId);

    public List<CarryForwardsEntity> findByStudentId(@Param("StudentId") int studentId);
}
