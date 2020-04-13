package com.silasonyango.transactionservice.entity_classes.user_management;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "roles")
@javax.persistence.SqlResultSetMapping(
        name = "roles", entities =
@javax.persistence.EntityResult(entityClass = RolesEntity.class)
)

@NamedNativeQueries({
        @NamedNativeQuery(name="RolesEntity.getRoles",
                query="SELECT * FROM users",
                resultSetMapping = "roles" )
})

public class RolesEntity implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoleId")
    private int roleId;

    @Column(name = "RoleDescription")
    private String roleDescription;

    @Column(name = "RoleCode")
    private int roleCode;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public int getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(int roleCode) {
        this.roleCode = roleCode;
    }
}
