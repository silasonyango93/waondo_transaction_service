package com.silasonyango.transactionservice.repository.system_initialization.system_configuration;

import com.silasonyango.transactionservice.entity_classes.system_initialization.system_configuration.SystemConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemConfigurationRepository extends JpaRepository<SystemConfigurationEntity, Long> {
}
