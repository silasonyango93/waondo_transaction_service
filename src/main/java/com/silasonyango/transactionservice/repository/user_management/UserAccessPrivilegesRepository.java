package com.silasonyango.transactionservice.repository.user_management;

import com.silasonyango.transactionservice.entity_classes.user_management.UserAccessPrivilegesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserAccessPrivilegesRepository extends JpaRepository<UserAccessPrivilegesEntity, Long> {
    public List<UserAccessPrivilegesEntity> findByUserRoleId(@Param("UserRoleId") int userRoleId);
}
