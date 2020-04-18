package com.silasonyango.transactionservice.repository.user_management;

import com.silasonyango.transactionservice.entity_classes.user_management.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RolesRepository extends JpaRepository<RolesEntity, Long> {
    public List<RolesEntity> findByRoleId(@Param("RoleId") int roleId);
    public List<RolesEntity> findByRoleCode(@Param("RoleCode") int roleCode);
}
