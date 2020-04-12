package com.silasonyango.transactionservice.controllers.user_management;

import com.silasonyango.transactionservice.dtos.api_response.SuccessFailureResponseDto;
import com.silasonyango.transactionservice.dtos.user_management.UserIdDto;
import com.silasonyango.transactionservice.entity_classes.user_management.RolesEntity;
import com.silasonyango.transactionservice.entity_classes.user_management.UserRolesEntity;
import com.silasonyango.transactionservice.repository.user_management.RolesRepository;
import com.silasonyango.transactionservice.repository.user_management.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user_roles")
public class UserRolesController {
    @Autowired
    UserRolesRepository userRolesRepository;

    @Autowired
    RolesRepository rolesRepository;

    @PostMapping("/create_role")
    public UserRolesEntity createUseRole(@Valid UserRolesEntity userRolesEntity) {

        return userRolesRepository.save(userRolesEntity);
    }

    @PostMapping("/get_all_roles")
    public List<UserRolesEntity> getAllUserRoles() {
        return userRolesRepository.findAll();
    }

    @PostMapping("/assign_a_user_roles")
    public SuccessFailureResponseDto assignAUserRoles(@Valid UserIdDto userIdDto) {

        List<RolesEntity> rolesEntityList = rolesRepository.findAll();

        for(int i = 0;i<rolesEntityList.size();i++) {
            UserRolesEntity userRolesEntity = new UserRolesEntity();
            userRolesEntity.setRoleId(rolesEntityList.get(i).getRoleId());
            userRolesEntity.setUserId(userIdDto.getUserId());
            userRolesEntity.setConfirmationStatus("0");

            userRolesRepository.save(userRolesEntity);
        }

        return new SuccessFailureResponseDto(true, "User successfully assigned roles", "N/A");
    }
}
