package com.silasonyango.transactionservice.dtos.user_management;

import com.silasonyango.transactionservice.dtos.roles_and_access_privileges.UserRolesDto;
import com.silasonyango.transactionservice.entity_classes.session_management.SessionLogsEntity;

import java.util.List;

public class AuthenticationResponseDto {
    private boolean isLoginSuccessful;
    private String authenticationEventMessage;
    private int userId;
    private String name;
    private String email;
    private int genderId;
    private String userRegistrationDate;
    private List<UserRolesDto> userRolesDtoList;
    private SessionLogsEntity sessionLogsEntity;

    public boolean isLoginSuccessful() {
        return isLoginSuccessful;
    }

    public void setLoginSuccessful(boolean loginSuccessful) {
        isLoginSuccessful = loginSuccessful;
    }

    public String getAuthenticationEventMessage() {
        return authenticationEventMessage;
    }

    public void setAuthenticationEventMessage(String authenticationEventMessage) {
        this.authenticationEventMessage = authenticationEventMessage;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public String getUserRegistrationDate() {
        return userRegistrationDate;
    }

    public void setUserRegistrationDate(String userRegistrationDate) {
        this.userRegistrationDate = userRegistrationDate;
    }

    public List<UserRolesDto> getUserRolesDtoList() {
        return userRolesDtoList;
    }

    public void setUserRolesDtoList(List<UserRolesDto> userRolesDtoList) {
        this.userRolesDtoList = userRolesDtoList;
    }

    public SessionLogsEntity getSessionLogsEntity() {
        return sessionLogsEntity;
    }

    public void setSessionLogsEntity(SessionLogsEntity sessionLogsEntity) {
        this.sessionLogsEntity = sessionLogsEntity;
    }
}
