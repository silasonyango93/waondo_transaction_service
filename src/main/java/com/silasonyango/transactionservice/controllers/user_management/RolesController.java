package com.silasonyango.transactionservice.controllers.user_management;

import com.silasonyango.transactionservice.entity_classes.user_management.RolesEntity;
import com.silasonyango.transactionservice.repository.user_management.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesController {
    @Autowired
    RolesRepository rolesRepository;

    @PostMapping("/create_role")
    public RolesEntity createARole(@Valid RolesEntity rolesEntity) {

        return rolesRepository.save(rolesEntity);
    }

    @PostMapping("/get_all_roles")
    public List<RolesEntity> getAllRoles() {
        return rolesRepository.findAll();
    }
}
