package com.silasonyango.transactionservice.repository.session_management;

import com.silasonyango.transactionservice.entity_classes.session_management.UserSessionActivitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionActivitiesRepository extends JpaRepository<UserSessionActivitiesEntity, Long> {
}
