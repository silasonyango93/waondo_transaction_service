package com.silasonyango.transactionservice.repository.user_management;

import com.silasonyango.transactionservice.entity_classes.user_management.UserRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolesRepository  extends JpaRepository<UserRolesEntity, Long> {
}
