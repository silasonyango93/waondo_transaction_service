package com.silasonyango.transactionservice.repository.user_management;

import com.silasonyango.transactionservice.entity_classes.user_management.AccessPrivilegesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccessPrivilegesRepository extends JpaRepository<AccessPrivilegesEntity, Long> {
    public List<AccessPrivilegesEntity> findByAccessPrivilegeId(@Param("AccessPrivilegeId") int accessPrivilegeId);
}
