package com.silasonyango.transactionservice.controllers.user_management;

import com.silasonyango.transactionservice.entity_classes.user_management.UserAccessPrivilegesEntity;
import com.silasonyango.transactionservice.repository.user_management.UserAccessPrivilegesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user_access_privileges")
public class UserAccessPrivilegesController {

    @Autowired
    UserAccessPrivilegesRepository userAccessPrivilegesRepository;

    @PostMapping("/create_user_access_privileges")
    public UserAccessPrivilegesEntity createAUserAccessPrivilege(@Valid UserAccessPrivilegesEntity userAccessPrivilegesEntity) {

        return userAccessPrivilegesRepository.save(userAccessPrivilegesEntity);
    }

    @PostMapping("/get_all_roles")
    public List<UserAccessPrivilegesEntity> getAllUserAccessPrivileges() {
        return userAccessPrivilegesRepository.findAll();
    }
}

