package com.silasonyango.transactionservice.entity_classes.user_management;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "user_access_privileges")
@javax.persistence.SqlResultSetMapping(
        name = "user_access_privileges", entities =
@javax.persistence.EntityResult(entityClass = UserAccessPrivilegesEntity.class)
)

@NamedNativeQueries({
        @NamedNativeQuery(name="RolesEntity.getAllRoles",
                query="SELECT * FROM user_access_privileges",
                resultSetMapping = "user_access_privileges" )
})
public class UserAccessPrivilegesEntity implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserAccessPrivilegeId")
    private int userAccessPrivilegeId;

    @Column(name = "UserRoleId")
    private int userRoleId;

    @Column(name = "AccessPrivilegeId")
    private int accessPrivilegeId;

    @Column(name = "PermisionStatus")
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

    public int getPermissionStatus() {
        return permissionStatus;
    }

    public void setPermissionStatus(int permissionStatus) {
        this.permissionStatus = permissionStatus;
    }
}
