package com.silasonyango.transactionservice.entity_classes.user_management;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "access_privileges")
@javax.persistence.SqlResultSetMapping(
        name = "access_privileges", entities =
@javax.persistence.EntityResult(entityClass = AccessPrivilegesEntity.class)
)

@NamedNativeQueries({
        @NamedNativeQuery(name="UserRolesEntity.getAllRoles",
                query="SELECT * FROM access_privileges",
                resultSetMapping = "access_privileges" )
})
public class AccessPrivilegesEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccessPrivilegeId")
    private int accessPrivilegeId;

    @Column(name = "AccessPrivilegeDescription")
    private String accessPrivilegeDescription;

    @Column(name = "AccessPrivilegeCode")
    private int accessPrivilegeCode;

    public int getAccessPrivilegeId() {
        return accessPrivilegeId;
    }

    public void setAccessPrivilegeId(int accessPrivilegeId) {
        this.accessPrivilegeId = accessPrivilegeId;
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
