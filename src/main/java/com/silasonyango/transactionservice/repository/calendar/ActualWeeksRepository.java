package com.silasonyango.transactionservice.repository.calendar;

import com.silasonyango.transactionservice.entity_classes.calendar.ActualWeeksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActualWeeksRepository extends JpaRepository<ActualWeeksEntity, Long> {
    @Query(nativeQuery = true)
    public List<ActualWeeksEntity> findCurrentActualWeek(String todaysDate);
}
