package com.silasonyango.transactionservice.repository.session_management;

import com.silasonyango.transactionservice.entity_classes.session_management.SessionActivitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessionActivitiesRepository extends JpaRepository<SessionActivitiesEntity, Long> {
    public List<SessionActivitiesEntity> findBySessionActivityCode(@Param("SessionActivityCode") int sessionActivityCode);
}
