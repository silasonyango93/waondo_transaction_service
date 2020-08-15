package com.silasonyango.transactionservice.repository.calendar;

import com.silasonyango.transactionservice.entity_classes.calendar.WeekIterationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeekIterationRepository extends JpaRepository<WeekIterationsEntity, Long> {
}
