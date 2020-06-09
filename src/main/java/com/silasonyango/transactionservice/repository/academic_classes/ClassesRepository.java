package com.silasonyango.transactionservice.repository.academic_classes;

import com.silasonyango.transactionservice.entity_classes.academic_classes.ClassesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassesRepository extends JpaRepository<ClassesEntity, Long> {
}
