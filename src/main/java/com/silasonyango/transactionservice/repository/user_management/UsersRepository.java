package com.silasonyango.transactionservice.repository.user_management;

import com.silasonyango.transactionservice.entity_classes.user_management.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRepository  extends JpaRepository<UsersEntity, Long> {
    public List<UsersEntity> findByEmail(@Param("Email") String email);
}
