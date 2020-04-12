package com.silasonyango.transactionservice.dtos.roles_and_access_privileges;

import java.util.List;

public class UserDto {
    private int userId;
    private String name;
    private String email;
    private String genderId;
    private String registeredDate;
    private List<UserRolesDto> userRolesDtoList;

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

    public String getGenderId() {
        return genderId;
    }

    public void setGenderId(String genderId) {
        this.genderId = genderId;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public List<UserRolesDto> getUserRolesDtoList() {
        return userRolesDtoList;
    }

    public void setUserRolesDtoList(List<UserRolesDto> userRolesDtoList) {
        this.userRolesDtoList = userRolesDtoList;
    }
}
