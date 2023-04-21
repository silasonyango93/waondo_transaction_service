package com.silasonyango.transactionservice.repository.fee_management;

import com.silasonyango.transactionservice.entity_classes.fee_management.InstallmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InstallmentRepository extends JpaRepository<InstallmentsEntity, Long> {
    public List<InstallmentsEntity> findInstallmentsByStudentId(@Param("StudentId") int studentId);
    public List<InstallmentsEntity> findInstallmentByIsAdminInstallment(@Param("IsAdminInstallment") int isAdminInstallment);
    public InstallmentsEntity findInstallmentByInstallmentId(@Param("InstallmentId") int installmentId);
    public List<InstallmentsEntity> findInstallmentsNotSoftDeleted(int studentId);

    void deleteByStudentId(@Param("StudentId") int studentId);
}
