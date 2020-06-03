package com.silasonyango.transactionservice.repository.calendar;

import com.silasonyango.transactionservice.entity_classes.calendar.ActualWeeksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActualWeeksRepository extends JpaRepository<ActualWeeksEntity, Long> {
}
