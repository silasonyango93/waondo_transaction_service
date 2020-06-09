package com.silasonyango.transactionservice.repository.session_management;

import com.silasonyango.transactionservice.entity_classes.session_management.SessionLogsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessionLogsRepository extends JpaRepository<SessionLogsEntity, Long> {
    public List<SessionLogsEntity> findByIsAdminSessionLog(@Param("IsAdminSessionLog") int isAdminSessionLog);
}
