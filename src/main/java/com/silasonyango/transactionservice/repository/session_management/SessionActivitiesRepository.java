package com.silasonyango.transactionservice.repository.session_management;

import com.silasonyango.transactionservice.entity_classes.session_management.SessionActivitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionActivitiesRepository extends JpaRepository<SessionActivitiesEntity, Long> {
}
