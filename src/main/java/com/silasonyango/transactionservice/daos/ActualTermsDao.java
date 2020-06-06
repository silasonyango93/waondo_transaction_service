package com.silasonyango.transactionservice.daos;

import com.silasonyango.transactionservice.entity_classes.calendar.ActualTermsEntity;
import com.silasonyango.transactionservice.repository.calendar.ActualTermsRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class ActualTermsDao implements ActualTermsRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<ActualTermsEntity> getTheLatestRegisteredTerm() {
        return null;
    }

    @Override
    public List<ActualTermsEntity> findActualTermByTermIterationIdAndYear(int termIterationId, String year) {
        List<ActualTermsEntity> actualTermsEntityList = manager.createNamedQuery("ActualTermsEntity.findActualTermByTermIterationIdAndYear", ActualTermsEntity.class)
                .setParameter(1, termIterationId)
                .setParameter(2, year)
                .getResultList();
        return actualTermsEntityList;
    }

    @Override
    public List<ActualTermsEntity> findAll() {
        return null;
    }

    @Override
    public List<ActualTermsEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<ActualTermsEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<ActualTermsEntity> findAllById(Iterable<Long> iterable) {
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
    public void delete(ActualTermsEntity actualTermsEntity) {

    }

    @Override
    public void deleteAll(Iterable<? extends ActualTermsEntity> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends ActualTermsEntity> S save(S s) {
        return null;
    }

    @Override
    public <S extends ActualTermsEntity> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<ActualTermsEntity> findById(Long aLong) {
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
    public <S extends ActualTermsEntity> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<ActualTermsEntity> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public ActualTermsEntity getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends ActualTermsEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ActualTermsEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends ActualTermsEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends ActualTermsEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ActualTermsEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ActualTermsEntity> boolean exists(Example<S> example) {
        return false;
    }
}
