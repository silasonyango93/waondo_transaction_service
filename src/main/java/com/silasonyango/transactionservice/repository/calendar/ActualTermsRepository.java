package com.silasonyango.transactionservice.repository.calendar;

import com.silasonyango.transactionservice.entity_classes.calendar.ActualTermsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActualTermsRepository extends JpaRepository<ActualTermsEntity, Long> {

    @Query(nativeQuery = true)
    public List<ActualTermsEntity> getTheLatestRegisteredTerm();

    public List<ActualTermsEntity> findActualTermByTermIterationIdAndYear(int termIterationId,String year);
}
