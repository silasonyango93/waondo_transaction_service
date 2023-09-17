package com.silasonyango.transactionservice.dtos.daos.calendar;

import com.silasonyango.transactionservice.entity_classes.calendar.TermIterationsEntity;
import com.silasonyango.transactionservice.repository.calendar.TermIterationsRepository;
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
public class TermIterationsDao implements TermIterationsRepository {
    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public List<TermIterationsEntity> findActualTermByTermIterationId(int termIterationId) {
        List<TermIterationsEntity> termIterationsEntityList = manager.createNamedQuery("TermIterationsEntity.findActualTermByTermIterationId", TermIterationsEntity.class)
                .setParameter(1, termIterationId)
                .getResultList();
        return termIterationsEntityList;
    }

    @Override
    public List<TermIterationsEntity> getAllTermIterationsInAscendingOrder() {
        return null;
    }

    @Override
    public List<TermIterationsEntity> findAll() {
        return null;
    }

    @Override
    public List<TermIterationsEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<TermIterationsEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<TermIterationsEntity> findAllById(Iterable<Long> iterable) {
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
    public void delete(TermIterationsEntity termIterationsEntity) {

    }

    @Override
    public void deleteAll(Iterable<? extends TermIterationsEntity> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends TermIterationsEntity> S save(S s) {
        return null;
    }

    @Override
    public <S extends TermIterationsEntity> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<TermIterationsEntity> findById(Long aLong) {
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
    public <S extends TermIterationsEntity> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<TermIterationsEntity> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public TermIterationsEntity getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends TermIterationsEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends TermIterationsEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends TermIterationsEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends TermIterationsEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends TermIterationsEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends TermIterationsEntity> boolean exists(Example<S> example) {
        return false;
    }
}
