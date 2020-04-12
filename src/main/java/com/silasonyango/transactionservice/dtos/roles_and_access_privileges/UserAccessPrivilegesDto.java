package com.silasonyango.transactionservice.dtos.roles_and_access_privileges;

public class UserAccessPrivilegesDto {
    private int userAccessPrivilegeId;
    private int userRoleId;
    private int accessPrivilegeId;
    private int permissionStatus;

    public int getUserAccessPrivilegeId() {
        return userAccessPrivilegeId;
    }

    public void setUserAccessPrivilegeId(int userAccessPrivilegeId) {
        this.userAccessPrivilegeId = userAccessPrivilegeId;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public int getAccessPrivilegeId() {
        return accessPrivilegeId;
    }

    public void setAccessPrivilegeId(int accessPrivilegeId) {
        this.accessPrivilegeId = accessPrivilegeId;
    }

    public int getPermisionStatus() {
        return permissionStatus;
    }

    public void setPermisionStatus(int permisionStatus) {
        this.permissionStatus = permisionStatus;
    }
}
