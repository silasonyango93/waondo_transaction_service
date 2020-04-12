package com.silasonyango.transactionservice.controllers.user_management;

import com.silasonyango.transactionservice.dtos.roles_and_access_privileges.UserAccessPrivilegesDto;
import com.silasonyango.transactionservice.dtos.roles_and_access_privileges.UserDto;
import com.silasonyango.transactionservice.dtos.roles_and_access_privileges.UserRolesDto;
import com.silasonyango.transactionservice.entity_classes.user_management.UserAccessPrivilegesEntity;
import com.silasonyango.transactionservice.entity_classes.user_management.UserRolesEntity;
import com.silasonyango.transactionservice.entity_classes.user_management.UsersEntity;
import com.silasonyango.transactionservice.repository.user_management.UserAccessPrivilegesRepository;
import com.silasonyango.transactionservice.repository.user_management.UserRolesRepository;
import com.silasonyango.transactionservice.repository.user_management.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UserRolesRepository userRolesRepository;

    @Autowired
    UserAccessPrivilegesRepository userAccessPrivilegesRepository;

    @PostMapping("/create_a_user")
    public UsersEntity createAUser(@Valid UsersEntity usersEntity) {

        return usersRepository.save(usersEntity);
    }

    @PostMapping("/get_users_roles_and_access_privileges")
    public List<UserDto> getUsersRolesAndAccessPrivileges() {

        List<UserDto> userDtoList = new ArrayList<UserDto>();
        List<UsersEntity> usersEntityList = usersRepository.findAll();


        for(int i = 0;i<usersEntityList.size();i++) {

            UserDto userDto = new UserDto();

            userDto.setUserId(usersEntityList.get(i).getUserId());
            userDto.setName(usersEntityList.get(i).getName());
            userDto.setEmail(usersEntityList.get(i).getEmail());
            userDto.setGenderId(usersEntityList.get(i).getGenderId());
            userDto.setRegisteredDate(usersEntityList.get(i).getRegisteredDate());



            List<UserRolesEntity> userRolesEntityList = userRolesRepository.findByUserId(usersEntityList.get(i).getUserId());
            List<UserRolesDto> userRolesDtoList = new ArrayList<UserRolesDto>();

            for(int k = 0;k<userRolesEntityList.size();k++) {
                UserRolesDto userRolesDto = new UserRolesDto();
                userRolesDto.setUserRoleId(userRolesEntityList.get(k).getUserRoleId());
                userRolesDto.setRoleId(userRolesEntityList.get(k).getRoleId());
                userRolesDto.setUserId(userRolesEntityList.get(k).getUserId());
                userRolesDto.setConfirmationStatus(userRolesEntityList.get(k).getConfirmationStatus());

                userRolesDtoList.add(userRolesDto);
            }




            for(int j = 0;j<userRolesDtoList.size();j++) {

                List<UserAccessPrivilegesEntity> userAccessPrivilegesEntityList = userAccessPrivilegesRepository.findByUserRoleId(userRolesDtoList.get(j).getUserRoleId());
                List<UserAccessPrivilegesDto> userAccessPrivilegesDtoList = new ArrayList<UserAccessPrivilegesDto>();

                for(int r = 0;r<userAccessPrivilegesEntityList.size();r++) {
                    UserAccessPrivilegesDto userAccessPrivilegesDto = new UserAccessPrivilegesDto();
                    userAccessPrivilegesDto.setAccessPrivilegeId(userAccessPrivilegesEntityList.get(r).getAccessPrivilegeId());
                    userAccessPrivilegesDto.setPermisionStatus(userAccessPrivilegesEntityList.get(r).getPermissionStatus());
                    userAccessPrivilegesDto.setUserAccessPrivilegeId(userAccessPrivilegesEntityList.get(r).getUserAccessPrivilegeId());
                    userAccessPrivilegesDto.setUserRoleId(userAccessPrivilegesEntityList.get(r).getUserRoleId());

                    userAccessPrivilegesDtoList.add(userAccessPrivilegesDto);
                }

                userRolesDtoList.get(j).setUserAccessPrivilegesDtoList(userAccessPrivilegesDtoList);

            }

            userDto.setUserRolesDtoList(userRolesDtoList);
            userDtoList.add(userDto);
        }


        return userDtoList;
    }
}
