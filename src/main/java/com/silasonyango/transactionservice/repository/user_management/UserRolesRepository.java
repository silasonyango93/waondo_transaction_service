package com.silasonyango.transactionservice.repository.user_management;

import com.silasonyango.transactionservice.entity_classes.user_management.UserRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRolesRepository  extends JpaRepository<UserRolesEntity, Long> {
    public List<UserRolesEntity> findByUserId(@Param("UserId") int userId);
}
