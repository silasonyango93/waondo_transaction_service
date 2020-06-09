package com.silasonyango.transactionservice.repository.system_initialization.gender;

import com.silasonyango.transactionservice.entity_classes.system_initialization.gender.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<GenderEntity, Long> {
}
