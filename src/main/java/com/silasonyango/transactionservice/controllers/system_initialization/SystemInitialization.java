package com.silasonyango.transactionservice.controllers.system_initialization;

import com.silasonyango.transactionservice.entity_classes.system_initialization.gender.GenderEntity;
import com.silasonyango.transactionservice.entity_classes.user_management.AccessPrivilegesEntity;
import com.silasonyango.transactionservice.repository.system_initialization.gender.GenderRepository;
import com.silasonyango.transactionservice.repository.system_initialization.system_configuration.SystemConfigurationRepository;
import com.silasonyango.transactionservice.repository.user_management.AccessPrivilegesRepository;
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

    @EventListener(ApplicationReadyEvent.class)
    public void checkIfSystemIsAlreadyConfigured() {

        if(systemConfigurationRepository.findAll().size() == 0) {
            //configureGender();
            configureAccessPrivileges();
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
}
