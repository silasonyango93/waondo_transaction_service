package com.silasonyango.transactionservice.repository.user_management;

import com.silasonyango.transactionservice.entity_classes.user_management.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository  extends JpaRepository<UsersEntity, Long> {
}