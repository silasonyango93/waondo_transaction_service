package com.silasonyango.transactionservice.dtos.roles_and_access_privileges;

import java.util.List;

public class UserRolesDto {
    private int userRoleId;
    private int userId;
    private int roleId;
    private int confirmationStatus;
    private List<UserAccessPrivilegesDto> userAccessPrivilegesDtoList;


    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getConfirmationStatus() {
        return confirmationStatus;
    }

    public void setConfirmationStatus(int confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
    }

    public List<UserAccessPrivilegesDto> getUserAccessPrivilegesDtoList() {
        return userAccessPrivilegesDtoList;
    }

    public void setUserAccessPrivilegesDtoList(List<UserAccessPrivilegesDto> userAccessPrivilegesDtoList) {
        this.userAccessPrivilegesDtoList = userAccessPrivilegesDtoList;
    }
}
