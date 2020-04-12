package com.silasonyango.transactionservice.controllers.user_management;

import com.silasonyango.transactionservice.entity_classes.user_management.AccessPrivilegesEntity;
import com.silasonyango.transactionservice.repository.user_management.AccessPrivilegesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/access_privileges")
public class AccessPrivilegesController {
    @Autowired
    AccessPrivilegesRepository accessPrivilegesRepository;

    @PostMapping("/create_access_privilege")
    public AccessPrivilegesEntity createAnAccessPrivilege(@Valid AccessPrivilegesEntity accessPrivilegesEntity) {

        return accessPrivilegesRepository.save(accessPrivilegesEntity);
    }

    @PostMapping("/get_all_access_privileges")
    public List<AccessPrivilegesEntity> getAllAccessPrivileges() {
        return accessPrivilegesRepository.findAll();
    }
}
