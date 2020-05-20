package com.silasonyango.transactionservice.repository.student_management;

import com.silasonyango.transactionservice.entity_classes.student_management.StudentRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRegistrationRepository extends JpaRepository<StudentRegistrationEntity, Long> {
}
