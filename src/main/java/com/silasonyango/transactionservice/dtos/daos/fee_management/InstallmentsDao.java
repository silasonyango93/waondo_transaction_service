package com.silasonyango.transactionservice.dtos.daos.fee_management;

import com.silasonyango.transactionservice.entity_classes.calendar.ActualTermsEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.InstallmentsEntity;
import com.silasonyango.transactionservice.repository.fee_management.InstallmentRepository;
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
public class InstallmentsDao implements InstallmentRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<InstallmentsEntity> findInstallmentsByStudentId(int studentId) {
        return null;
    }

    @Override
    public List<InstallmentsEntity> findInstallmentByIsAdminInstallment(int isAdminInstallment) {
        return null;
    }

    @Override
    public InstallmentsEntity findInstallmentByInstallmentId(int installmentId) {
        return null;
    }

    @Override
    public List<InstallmentsEntity> findInstallmentsNotSoftDeleted(int studentId) {
        List<InstallmentsEntity> installmentsEntityList = manager.createNamedQuery("InstallmentsEntity.findInstallmentsNotSoftDeleted", InstallmentsEntity.class)
                .setParameter(1, studentId)
                .getResultList();
        return installmentsEntityList;
    }

    @Override
    public void deleteByStudentId(int studentId) {

    }

    @Override
    public List<InstallmentsEntity> findAll() {
        return null;
    }

    @Override
    public List<InstallmentsEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<InstallmentsEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<InstallmentsEntity> findAllById(Iterable<Long> iterable) {
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
    public void delete(InstallmentsEntity installmentsEntity) {

    }

    @Override
    public void deleteAll(Iterable<? extends InstallmentsEntity> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends InstallmentsEntity> S save(S s) {
        return null;
    }

    @Override
    public <S extends InstallmentsEntity> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<InstallmentsEntity> findById(Long aLong) {
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
    public <S extends InstallmentsEntity> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<InstallmentsEntity> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public InstallmentsEntity getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends InstallmentsEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends InstallmentsEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends InstallmentsEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends InstallmentsEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends InstallmentsEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends InstallmentsEntity> boolean exists(Example<S> example) {
        return false;
    }
}
