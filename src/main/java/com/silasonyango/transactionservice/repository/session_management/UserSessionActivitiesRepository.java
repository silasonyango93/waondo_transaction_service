package com.silasonyango.transactionservice.repository.session_management;

import com.silasonyango.transactionservice.entity_classes.session_management.UserSessionActivitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserSessionActivitiesRepository extends JpaRepository<UserSessionActivitiesEntity, Long> {
    public List<UserSessionActivitiesEntity> findByIsAdminUserSessionActivity(@Param("IsAdminUserSessionActivity") int isAdminUserSessionActivity);
}
