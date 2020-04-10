package com.silasonyango.personallibrary.models.authentication;

import javax.persistence.*;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@javax.persistence.Entity
@Table(name = "users")
@javax.persistence.SqlResultSetMapping(
        name = "users", entities =
@javax.persistence.EntityResult(entityClass = UserModel.class)
)

@NamedNativeQueries({
        @NamedNativeQuery(name="UserModel.getUsers",
                query="SELECT * FROM users",
        resultSetMapping = "users" )
})

public class UserModel  implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UserId")
    private int UserId;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "MiddleName")
    private String middleName;

    @Column(name = "Surname")
    private String surname;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "EncryptedPassword")
    @Transient
    private String password;

    public int getUser() {
        return UserId;
    }

    public void setUser(int UserId) {
        this.UserId = UserId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
