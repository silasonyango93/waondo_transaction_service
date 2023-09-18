package com.silasonyango.transactionservice.dtos.daos.calendar;

import com.silasonyango.transactionservice.entity_classes.calendar.ActualWeeksEntity;
import com.silasonyango.transactionservice.repository.calendar.ActualWeeksRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ActualWeeksDao implements ActualWeeksRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<ActualWeeksEntity> findCurrentActualWeek(String todaysDate) {
        List<ActualWeeksEntity> actualWeeksEntityList = manager.createNamedQuery("ActualWeeksEntity.findCurrentActualWeek", ActualWeeksEntity.class)
                .setParameter(1, todaysDate)
                .getResultList();
        return actualWeeksEntityList;
    }

    @Override
    public List<ActualWeeksEntity> findByTermId(int termId) {
        return null;
    }

    @Override
    public List<ActualWeeksEntity> findAll() {
        return null;
    }

    @Override
    public List<ActualWeeksEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<ActualWeeksEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<ActualWeeksEntity> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(ActualWeeksEntity actualWeeksEntity) {

    }

    @Override
    public void deleteAll(Iterable<? extends ActualWeeksEntity> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends ActualWeeksEntity> S save(S s) {
        return null;
    }

    @Override
    public <S extends ActualWeeksEntity> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<ActualWeeksEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends ActualWeeksEntity> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<ActualWeeksEntity> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public ActualWeeksEntity getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends ActualWeeksEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ActualWeeksEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends ActualWeeksEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends ActualWeeksEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ActualWeeksEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ActualWeeksEntity> boolean exists(Example<S> example) {
        return false;
    }
}
