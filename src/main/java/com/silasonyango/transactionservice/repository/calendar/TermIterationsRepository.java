package com.silasonyango.transactionservice.repository.calendar;

import com.silasonyango.transactionservice.entity_classes.calendar.TermIterationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TermIterationsRepository extends JpaRepository<TermIterationsEntity, Long> {
    public List<TermIterationsEntity> findActualTermByTermIterationId(int termIterationId);

    @Query(nativeQuery = true)
    public List<TermIterationsEntity> getAllTermIterationsInAscendingOrder();
}
