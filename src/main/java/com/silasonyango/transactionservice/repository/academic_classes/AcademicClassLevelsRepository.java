package com.silasonyango.transactionservice.repository.academic_classes;

import com.silasonyango.transactionservice.entity_classes.academic_classes.AcademicClassLevelsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AcademicClassLevelsRepository extends JpaRepository<AcademicClassLevelsEntity, Long> {
    public List<AcademicClassLevelsEntity> findByAcademicClassLevelId(@Param("AcademicClassLevelId") int academicClassLevelId);
}
