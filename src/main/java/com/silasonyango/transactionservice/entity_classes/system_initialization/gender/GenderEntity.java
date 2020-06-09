package com.silasonyango.transactionservice.entity_classes.system_initialization.gender;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "gender")
public class GenderEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GenderId")
    private int genderId;

    @Column(name = "GenderDescription")
    private String genderDescription;

    @Column(name = "GenderCode")
    private int genderCode;

    public GenderEntity() {
    }

    public GenderEntity(String genderDescription, int genderCode) {
        this.genderDescription = genderDescription;
        this.genderCode = genderCode;
    }

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public String getGenderDescription() {
        return genderDescription;
    }

    public void setGenderDescription(String genderDescription) {
        this.genderDescription = genderDescription;
    }

    public int getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(int genderCode) {
        this.genderCode = genderCode;
    }
}
