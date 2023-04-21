package com.silasonyango.transactionservice.repository.student_management;

import com.silasonyango.transactionservice.entity_classes.student_management.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    @Query(nativeQuery = true)
    public List<StudentEntity> getAllNoneAdminStudents();

    public List<StudentEntity> findByAdmissionNo(@Param("AdmissionNo") String admissionNo);
    public List<StudentEntity> findByStudentId(@Param("StudentId") int studentId);
    public List<StudentEntity> findByIsAnAdminStudent(@Param("IsAnAdminStudent") int isAnAdminStudent);

    void deleteByStudentId(@Param("StudentId") int studentId);
}
