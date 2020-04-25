package com.silasonyango.transactionservice.controllers.user_management;

import com.silasonyango.transactionservice.common.config.EndPoints;
import com.silasonyango.transactionservice.common.config.SessionActivitiesConfig;
import com.silasonyango.transactionservice.dtos.api_response.SuccessFailureResponseDto;
import com.silasonyango.transactionservice.dtos.roles_and_access_privileges.UserAccessPrivilegesDto;
import com.silasonyango.transactionservice.dtos.roles_and_access_privileges.UserDto;
import com.silasonyango.transactionservice.dtos.roles_and_access_privileges.UserRolesDto;
import com.silasonyango.transactionservice.dtos.user_management.AuthenticationRequestDto;
import com.silasonyango.transactionservice.dtos.user_management.AuthenticationResponseDto;
import com.silasonyango.transactionservice.dtos.user_management.UserIdDto;
import com.silasonyango.transactionservice.entity_classes.session_management.SessionLogsEntity;
import com.silasonyango.transactionservice.entity_classes.session_management.UserSessionActivitiesEntity;
import com.silasonyango.transactionservice.entity_classes.user_management.*;
import com.silasonyango.transactionservice.repository.session_management.SessionLogsRepository;
import com.silasonyango.transactionservice.repository.session_management.UserSessionActivitiesRepository;
import com.silasonyango.transactionservice.repository.user_management.*;
import com.silasonyango.transactionservice.utility_classes.CustomOkHttp;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UserRolesRepository userRolesRepository;

    @Autowired
    UserAccessPrivilegesRepository userAccessPrivilegesRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    AccessPrivilegesRepository accessPrivilegesRepository;

    @Autowired
    SessionLogsRepository sessionLogsRepository;

    @Autowired
    UserSessionActivitiesRepository userSessionActivitiesRepository;

    @PostMapping("/create_user")
    public UserDto createUser(@Valid UsersEntity user) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getEncryptedPassword());
        user.setEncryptedPassword(hashedPassword);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        user.setRegisteredDate(dtf.format(now));

        UsersEntity savedUser = usersRepository.save(user);

        UserIdDto userIdDto = new UserIdDto();
        userIdDto.setUserId(savedUser.getUserId());
        SuccessFailureResponseDto successStatus = assignAUserRoles(userIdDto);

        UserDto userDto = new UserDto();

        if(successStatus.getSuccessStatus()) {
            userDto.setUserId(savedUser.getUserId());
            userDto.setName(savedUser.getName());
            userDto.setEmail(savedUser.getEmail());
            userDto.setGenderId(savedUser.getGenderId());
            userDto.setRegisteredDate(savedUser.getRegisteredDate());


            List<UserRolesEntity> userRolesEntityList = userRolesRepository.findByUserId(savedUser.getUserId());
            List<UserRolesDto> userRolesDtoList = new ArrayList<UserRolesDto>();

            for(int k = 0;k<userRolesEntityList.size();k++) {
                UserRolesDto userRolesDto = new UserRolesDto();
                userRolesDto.setUserRoleId(userRolesEntityList.get(k).getUserRoleId());
                userRolesDto.setRoleId(userRolesEntityList.get(k).getRoleId());
                userRolesDto.setUserId(userRolesEntityList.get(k).getUserId());
                userRolesDto.setConfirmationStatus(userRolesEntityList.get(k).getConfirmationStatus());
                userRolesDto.setRoleDescription(getRoleDescription(userRolesEntityList.get(k).getRoleId()));
                userRolesDto.setRoleCode(getRoleCode(userRolesEntityList.get(k).getRoleId()));

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
                    userAccessPrivilegesDto.setAccessPrivilegeDescription(getAccessPrivilegeDescription(userAccessPrivilegesEntityList.get(r).getAccessPrivilegeId()));
                    userAccessPrivilegesDto.setAccessPrivilegeCode(getAccessPrivilegeCode(userAccessPrivilegesEntityList.get(r).getAccessPrivilegeId()));

                    userAccessPrivilegesDtoList.add(userAccessPrivilegesDto);
                }

                userRolesDtoList.get(j).setUserAccessPrivilegesDtoList(userAccessPrivilegesDtoList);

            }

            userDto.setUserRolesDtoList(userRolesDtoList);
            userDto.setUserSuccessfullyCreated(true);
        }

        return userDto;
    }



    @PostMapping("/authenticate")
    public AuthenticationResponseDto authenticate(@Valid AuthenticationRequestDto authenticationRequestDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AuthenticationResponseDto authenticationResponseDto = new AuthenticationResponseDto();
        List<UsersEntity> usersEntityList = usersRepository.findByEmail(authenticationRequestDto.getAttemptedEmail());

        if(usersEntityList.size() == 0) {
            authenticationResponseDto.setLoginSuccessful(false);
            authenticationResponseDto.setAuthenticationEventMessage("No user exists by this email");
        } else if(usersEntityList.size() == 1) {
            UsersEntity user = usersEntityList.get(0);


            if(passwordEncoder.matches(authenticationRequestDto.getAttemptedPassword(),user.getEncryptedPassword())){

                CustomOkHttp customOkHttp = new CustomOkHttp();

                RequestBody formBody = new FormBody.Builder()
                        .add("userId", String.valueOf(user.getUserId()))
                        .add("roleCode", String.valueOf(authenticationRequestDto.getAttemtedRoleCode()))
                        .build();

                try {
                    String responseString = customOkHttp.okHttpPostPassingParams(EndPoints.WAONDO_NODE_BASE_URL + "/user_has_certain_role",formBody);

                    JSONObject object = new JSONObject(responseString);
                    JSONArray jsonArray = object.getJSONArray("results");

                    if(jsonArray.length() == 0) {
                        authenticationResponseDto.setLoginSuccessful(false);
                        authenticationResponseDto.setAuthenticationEventMessage("Current user not assigned this role");
                    } else if(jsonArray.length() > 0) {

                        JSONObject obj = null;
                        obj = jsonArray.getJSONObject(0);

                        if(isUserAllowedLoginWithThisRole(obj.getInt("UserRoleId"))) {
                            authenticationResponseDto.setLoginSuccessful(true);
                            authenticationResponseDto.setAuthenticationEventMessage("Login successful");
                            authenticationResponseDto.setUserId(user.getUserId());
                            authenticationResponseDto.setName(user.getName());
                            authenticationResponseDto.setEmail(user.getEmail());
                            authenticationResponseDto.setGenderId(user.getGenderId());
                            authenticationResponseDto.setUserRegistrationDate(user.getRegisteredDate());

                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            LocalDateTime now = LocalDateTime.now();
                            SessionLogsEntity returnedSession = sessionLogsRepository.save(new SessionLogsEntity(user.getUserId(),dtf.format(now)));
                            authenticationResponseDto.setSessionLogsEntity(returnedSession);

                            userSessionActivitiesRepository.save(new UserSessionActivitiesEntity(returnedSession.getSessionLogId(), SessionActivitiesConfig.LOGIN_SESSION_ACTIVITY, dtf.format(now)));

                            List<UserRolesEntity> userRolesEntityList = userRolesRepository.findByUserId(user.getUserId());
                            List<UserRolesDto> userRolesDtoList = new ArrayList<UserRolesDto>();

                            for(int k = 0;k<userRolesEntityList.size();k++) {
                                UserRolesDto userRolesDto = new UserRolesDto();
                                userRolesDto.setUserRoleId(userRolesEntityList.get(k).getUserRoleId());
                                userRolesDto.setRoleId(userRolesEntityList.get(k).getRoleId());
                                userRolesDto.setUserId(userRolesEntityList.get(k).getUserId());
                                userRolesDto.setConfirmationStatus(userRolesEntityList.get(k).getConfirmationStatus());
                                userRolesDto.setRoleDescription(getRoleDescription(userRolesEntityList.get(k).getRoleId()));
                                userRolesDto.setRoleCode(getRoleCode(userRolesEntityList.get(k).getRoleId()));

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
                                    userAccessPrivilegesDto.setAccessPrivilegeDescription(getAccessPrivilegeDescription(userAccessPrivilegesEntityList.get(r).getAccessPrivilegeId()));
                                    userAccessPrivilegesDto.setAccessPrivilegeCode(getAccessPrivilegeCode(userAccessPrivilegesEntityList.get(r).getAccessPrivilegeId()));

                                    userAccessPrivilegesDtoList.add(userAccessPrivilegesDto);
                                }

                                userRolesDtoList.get(j).setUserAccessPrivilegesDtoList(userAccessPrivilegesDtoList);

                            }

                            authenticationResponseDto.setUserRolesDtoList(userRolesDtoList);
                        } else {
                            authenticationResponseDto.setLoginSuccessful(false);
                            authenticationResponseDto.setAuthenticationEventMessage("Access rights not currently granted");
                        }


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    authenticationResponseDto.setLoginSuccessful(false);
                    authenticationResponseDto.setAuthenticationEventMessage("Error determining user access rights: " + e.toString());
                }



            }else {

                authenticationResponseDto.setLoginSuccessful(false);
                authenticationResponseDto.setAuthenticationEventMessage("Wrong email or password");

            }
        }



        return authenticationResponseDto;
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
                userRolesDto.setRoleDescription(getRoleDescription(userRolesEntityList.get(k).getRoleId()));
                userRolesDto.setRoleCode(getRoleCode(userRolesEntityList.get(k).getRoleId()));

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
                    userAccessPrivilegesDto.setAccessPrivilegeDescription(getAccessPrivilegeDescription(userAccessPrivilegesEntityList.get(r).getAccessPrivilegeId()));
                    userAccessPrivilegesDto.setAccessPrivilegeCode(getAccessPrivilegeCode(userAccessPrivilegesEntityList.get(r).getAccessPrivilegeId()));

                    userAccessPrivilegesDtoList.add(userAccessPrivilegesDto);
                }

                userRolesDtoList.get(j).setUserAccessPrivilegesDtoList(userAccessPrivilegesDtoList);

            }

            userDto.setUserRolesDtoList(userRolesDtoList);
            userDtoList.add(userDto);
        }


        return userDtoList;
    }

    public String getRoleDescription(int roleId) {
        List<RolesEntity> rolesEntityList = rolesRepository.findByRoleId(roleId);

        return rolesEntityList.get(0).getRoleDescription();
    }

    public int getRoleCode(int roleId) {
        List<RolesEntity> rolesEntityList = rolesRepository.findByRoleId(roleId);

        return rolesEntityList.get(0).getRoleCode();
    }

    public String getAccessPrivilegeDescription(int accessPrivilegeId) {
        List<AccessPrivilegesEntity> accessPrivilegesEntityList = accessPrivilegesRepository.findByAccessPrivilegeId(accessPrivilegeId);

        return accessPrivilegesEntityList.get(0).getAccessPrivilegeDescription();
    }

    public int getAccessPrivilegeCode(int accessPrivilegeId) {
        List<AccessPrivilegesEntity> accessPrivilegesEntityList = accessPrivilegesRepository.findByAccessPrivilegeId(accessPrivilegeId);

        return accessPrivilegesEntityList.get(0).getAccessPrivilegeCode();
    }

    public SuccessFailureResponseDto assignAUserRoles(@Valid UserIdDto userIdDto) {

        List<RolesEntity> rolesEntityList = rolesRepository.findAll();
        List<UserRolesEntity> dbUserRoles = new ArrayList<>();

        for(int i = 0;i<rolesEntityList.size();i++) {
            UserRolesEntity userRolesEntity = new UserRolesEntity();
            userRolesEntity.setRoleId(rolesEntityList.get(i).getRoleId());
            userRolesEntity.setUserId(userIdDto.getUserId());
            userRolesEntity.setConfirmationStatus(0);


            dbUserRoles.add(userRolesRepository.save(userRolesEntity));
        }

        List<AccessPrivilegesEntity> accessPrivilegesEntityList = accessPrivilegesRepository.findAll();


        for(int j = 0;j<dbUserRoles.size();j++) {

            for (int k = 0;k<accessPrivilegesEntityList.size();k++) {

                UserAccessPrivilegesEntity userAccessPrivilegesEntity = new UserAccessPrivilegesEntity();
                userAccessPrivilegesEntity.setUserRoleId(dbUserRoles.get(j).getUserRoleId());
                userAccessPrivilegesEntity.setAccessPrivilegeId(accessPrivilegesEntityList.get(k).getAccessPrivilegeId());
                userAccessPrivilegesEntity.setPermissionStatus(0);

                userAccessPrivilegesRepository.save(userAccessPrivilegesEntity);
            }
        }

        return new SuccessFailureResponseDto(true, "User successfully assigned roles", "N/A");
    }

    public int getRoleIdFromRoleCode(int roleCode) {
        List<RolesEntity> rolesEntityList = rolesRepository.findByRoleCode(roleCode);

        return rolesEntityList.get(0).getRoleId();
    }




    public boolean isUserAllowedLoginWithThisRole(int userRoleId) {
        boolean isUserAllowedLogin = false;
        CustomOkHttp customOkHttp = new CustomOkHttp();

        RequestBody formBody = new FormBody.Builder()
                .add("userRoleId", String.valueOf(userRoleId))
                .build();

        try {
            String responseString = customOkHttp.okHttpPostPassingParams(EndPoints.WAONDO_NODE_BASE_URL + "/user_allowed_login_with_certain_role",formBody);
            JSONObject object = new JSONObject(responseString);
            JSONArray jsonArray = object.getJSONArray("results");
            isUserAllowedLogin = jsonArray.length() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isUserAllowedLogin;
    }
}
