package com.silasonyango.transactionservice.entity_classes.user_management;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "users")
@javax.persistence.SqlResultSetMapping(
        name = "users", entities =
@javax.persistence.EntityResult(entityClass = UsersEntity.class)
)

@NamedNativeQueries({
        @NamedNativeQuery(name="UsersEntity.getUsers",
                query="SELECT * FROM users",
                resultSetMapping = "users" )
})
public class UsersEntity implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private int userId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Email")
    private String email;

    @Column(name = "GenderId")
    private int genderId;

    @Column(name = "EncryptedPassword")
    private String encryptedPassword;

    @Column(name = "RegisteredDate")
    private String registeredDate;

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

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }
}
