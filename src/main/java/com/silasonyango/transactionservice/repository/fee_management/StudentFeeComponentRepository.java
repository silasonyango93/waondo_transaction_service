package com.silasonyango.transactionservice.repository.fee_management;

import com.silasonyango.transactionservice.entity_classes.fee_management.StudentFeeComponentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentFeeComponentRepository extends JpaRepository<StudentFeeComponentEntity, Long> {
}
