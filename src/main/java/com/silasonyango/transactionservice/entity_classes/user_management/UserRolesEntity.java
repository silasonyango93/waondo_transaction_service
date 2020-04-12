package com.silasonyango.transactionservice.entity_classes.user_management;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "user_roles")
@javax.persistence.SqlResultSetMapping(
        name = "user_roles", entities =
@javax.persistence.EntityResult(entityClass = UserRolesEntity.class)
)

@NamedNativeQueries({
        @NamedNativeQuery(name="UserRolesEntity.getUserRoles",
                query="SELECT * FROM users",
                resultSetMapping = "user_roles" )
})
public class UserRolesEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserRoleId")
    private int userRoleId;

    @Column(name = "UserId")
    private String userId;

    @Column(name = "RoleId")
    private int roleId;

    @Column(name = "ConfirmationStatus")
    private String confirmationStatus;

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getConfirmationStatus() {
        return confirmationStatus;
    }

    public void setConfirmationStatus(String confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
    }
}
