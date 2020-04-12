package com.silasonyango.transactionservice.repository.user_management;

import com.silasonyango.transactionservice.entity_classes.user_management.UserAccessPrivilegesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccessPrivilegesRepository extends JpaRepository<UserAccessPrivilegesEntity, Long> {
}
