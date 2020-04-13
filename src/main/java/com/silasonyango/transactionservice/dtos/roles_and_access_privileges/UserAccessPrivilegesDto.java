package com.silasonyango.transactionservice.dtos.roles_and_access_privileges;

public class UserAccessPrivilegesDto {
    private int userAccessPrivilegeId;
    private int userRoleId;
    private int accessPrivilegeId;
    private int permissionStatus;
    private String accessPrivilegeDescription;
    private int accessPrivilegeCode;

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

    public String getAccessPrivilegeDescription() {
        return accessPrivilegeDescription;
    }

    public void setAccessPrivilegeDescription(String accessPrivilegeDescription) {
        this.accessPrivilegeDescription = accessPrivilegeDescription;
    }

    public int getAccessPrivilegeCode() {
        return accessPrivilegeCode;
    }

    public void setAccessPrivilegeCode(int accessPrivilegeCode) {
        this.accessPrivilegeCode = accessPrivilegeCode;
    }
}
