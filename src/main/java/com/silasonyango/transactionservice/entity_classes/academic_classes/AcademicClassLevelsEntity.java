package com.silasonyango.transactionservice.entity_classes.academic_classes;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "IsAdminClassLevel")
    private int isAdminClassLevel;

    @OneToMany(mappedBy = "academicClassLevelId",fetch = FetchType.LAZY)
    private List<LotsEntity> lotsEntities;


    public AcademicClassLevelsEntity() {
    }

    public AcademicClassLevelsEntity(String academicClassLevelName, int hierachyCode, int isAdminClassLevel) {
        this.academicClassLevelName = academicClassLevelName;
        this.hierachyCode = hierachyCode;
        this.isAdminClassLevel = isAdminClassLevel;
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

    public int getIsAdminClassLevel() {
        return isAdminClassLevel;
    }

    public void setIsAdminClassLevel(int isAdminClassLevel) {
        this.isAdminClassLevel = isAdminClassLevel;
    }

    public List<LotsEntity> getLotsEntities() {
        return lotsEntities;
    }

    public void setLotsEntities(List<LotsEntity> lotsEntities) {
        this.lotsEntities = lotsEntities;
    }
}
