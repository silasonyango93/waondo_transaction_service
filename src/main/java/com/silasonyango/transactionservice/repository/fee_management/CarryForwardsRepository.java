package com.silasonyango.transactionservice.repository.fee_management;

import com.silasonyango.transactionservice.entity_classes.fee_management.CarryForwardsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarryForwardsRepository extends JpaRepository<CarryForwardsEntity, Long> {
}
