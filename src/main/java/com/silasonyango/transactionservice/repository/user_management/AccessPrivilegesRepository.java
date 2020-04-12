package com.silasonyango.transactionservice.repository.user_management;

import com.silasonyango.transactionservice.entity_classes.user_management.AccessPrivilegesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessPrivilegesRepository extends JpaRepository<AccessPrivilegesEntity, Long> {
}
