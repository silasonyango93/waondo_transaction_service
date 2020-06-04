package com.silasonyango.transactionservice.entity_classes.academic_classes;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "academic_class_levels")
public class AcademicClassLevelsEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AcademicClassLevelId")
    private int academicClassLevelId;

    @Column(name = "AcademicClassLevelName")
    private String academicClassLevelName;

    @Column(name = "HierachyCode")
    private int hierachyCode;


    public AcademicClassLevelsEntity() {
    }

    public AcademicClassLevelsEntity(String academicClassLevelName, int hierachyCode) {
        this.academicClassLevelName = academicClassLevelName;
        this.hierachyCode = hierachyCode;
    }

    public int getAcademicClassLevelId() {
        return academicClassLevelId;
    }

    public void setAcademicClassLevelId(int academicClassLevelId) {
        this.academicClassLevelId = academicClassLevelId;
    }

    public String getAcademicClassLevelName() {
        return academicClassLevelName;
    }

    public void setAcademicClassLevelName(String academicClassLevelName) {
        this.academicClassLevelName = academicClassLevelName;
    }

    public int getHierachyCode() {
        return hierachyCode;
    }

    public void setHierachyCode(int hierachyCode) {
        this.hierachyCode = hierachyCode;
    }
}
