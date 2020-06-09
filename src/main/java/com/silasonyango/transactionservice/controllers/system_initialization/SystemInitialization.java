package com.silasonyango.transactionservice.controllers.system_initialization;

import com.silasonyango.transactionservice.entity_classes.fee_management.TransactionDescriptionsEntity;
import com.silasonyango.transactionservice.entity_classes.session_management.SessionActivitiesEntity;
import com.silasonyango.transactionservice.entity_classes.student_management.StudentResidenceEntity;
import com.silasonyango.transactionservice.entity_classes.system_initialization.correction_descriptions.CorrectionDescriptionsEntity;
import com.silasonyango.transactionservice.entity_classes.system_initialization.gender.GenderEntity;
import com.silasonyango.transactionservice.entity_classes.user_management.AccessPrivilegesEntity;
import com.silasonyango.transactionservice.entity_classes.user_management.RolesEntity;
import com.silasonyango.transactionservice.repository.fee_management.TransactionDescriptionsRepository;
import com.silasonyango.transactionservice.repository.session_management.SessionActivitiesRepository;
import com.silasonyango.transactionservice.repository.student_management.StudentResidenceRepository;
import com.silasonyango.transactionservice.repository.system_initialization.correction_descriptions.CorrectionDescriptionsRepository;
import com.silasonyango.transactionservice.repository.system_initialization.gender.GenderRepository;
import com.silasonyango.transactionservice.repository.system_initialization.system_configuration.SystemConfigurationRepository;
import com.silasonyango.transactionservice.repository.user_management.AccessPrivilegesRepository;
import com.silasonyango.transactionservice.repository.user_management.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system_initialization")
public class SystemInitialization {
    @Autowired
    SystemConfigurationRepository systemConfigurationRepository;

    @Autowired
    GenderRepository genderRepository;

    @Autowired
    AccessPrivilegesRepository accessPrivilegesRepository;

    @Autowired
    CorrectionDescriptionsRepository correctionDescriptionsRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    SessionActivitiesRepository sessionActivitiesRepository;

    @Autowired
    StudentResidenceRepository studentResidenceRepository;

    @Autowired
    TransactionDescriptionsRepository transactionDescriptionsRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void checkIfSystemIsAlreadyConfigured() {

        if(systemConfigurationRepository.findAll().size() == 0) {
            //configureGender();
            //configureAccessPrivileges();
            //configureCorrectionDescriptions();
           // configureRoles();
            //configureSessionActivities();
            //configureStudentResidence();
            configureTransactionDescriptions();
        } else {
            System.out.println("Configured");
        }

    }


    public void configureGender() {
        genderRepository.save(new GenderEntity("Male",1));
        genderRepository.save(new GenderEntity("Female",2));
    }

    public void configureAccessPrivileges() {
        accessPrivilegesRepository.save(new AccessPrivilegesEntity("Login",1));
        accessPrivilegesRepository.save(new AccessPrivilegesEntity("Register a student",2));
        accessPrivilegesRepository.save(new AccessPrivilegesEntity("Correct a student's personal details",3));
        accessPrivilegesRepository.save(new AccessPrivilegesEntity("Register a fee installment",4));
        accessPrivilegesRepository.save(new AccessPrivilegesEntity("Correct a fee payment",5));
        accessPrivilegesRepository.save(new AccessPrivilegesEntity("Delete a student",6));
    }

    public void configureCorrectionDescriptions() {
        correctionDescriptionsRepository.save(new CorrectionDescriptionsEntity("Wrong student paid for",1));
    }

    public void configureRoles() {
        rolesRepository.save(new RolesEntity("Admin",1));
        rolesRepository.save(new RolesEntity("Bursar",2));
    }

    public void configureSessionActivities() {
        sessionActivitiesRepository.save(new SessionActivitiesEntity("SYSTEM_CARRY_FORWARD_INSTALLMENT",0));
        sessionActivitiesRepository.save(new SessionActivitiesEntity("Logged into the system",1));
        sessionActivitiesRepository.save(new SessionActivitiesEntity("Registered a student",2));
        sessionActivitiesRepository.save(new SessionActivitiesEntity("Corrected a student's personal details",3));
        sessionActivitiesRepository.save(new SessionActivitiesEntity("Registered a fee installment",4));
        sessionActivitiesRepository.save(new SessionActivitiesEntity("Corrected a fee payment",5));
        sessionActivitiesRepository.save(new SessionActivitiesEntity("Deleted a student",6));
    }

    public void configureStudentResidence() {
        studentResidenceRepository.save(new StudentResidenceEntity("Boarder",1));
        studentResidenceRepository.save(new StudentResidenceEntity("Day Scholar",2));
    }

    public void configureTransactionDescriptions() {
        transactionDescriptionsRepository.save(new TransactionDescriptionsEntity("SYSTEM_CARRY_FORWARD_INSTALLMENT",0));
        transactionDescriptionsRepository.save(new TransactionDescriptionsEntity("Register a fee installment",1));
        transactionDescriptionsRepository.save(new TransactionDescriptionsEntity("Fee correction",2));
        transactionDescriptionsRepository.save(new TransactionDescriptionsEntity("An end of term carry forward",3));
        transactionDescriptionsRepository.save(new TransactionDescriptionsEntity("An end of year carry forward",4));
    }
}
