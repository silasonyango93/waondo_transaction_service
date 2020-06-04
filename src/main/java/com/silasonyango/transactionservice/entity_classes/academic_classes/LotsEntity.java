package com.silasonyango.transactionservice.entity_classes.academic_classes;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "lots")
public class LotsEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LotId")
    private int lotId;

    @Column(name = "LotDescriptionId")
    private int lotDescriptionId;

    @Column(name = "AcademicClassLevelId")
    private int academicClassLevelId;

    @Column(name = "RegisteredDate")
    private String registeredDate;

    public LotsEntity() {
    }

    public LotsEntity(int lotDescriptionId, int academicClassLevelId, String registeredDate) {
        this.lotDescriptionId = lotDescriptionId;
        this.academicClassLevelId = academicClassLevelId;
        this.registeredDate = registeredDate;
    }

    public int getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public int getLotDescriptionId() {
        return lotDescriptionId;
    }

    public void setLotDescriptionId(int lotDescriptionId) {
        this.lotDescriptionId = lotDescriptionId;
    }

    public int getAcademicClassLevelId() {
        return academicClassLevelId;
    }

    public void setAcademicClassLevelId(int academicClassLevelId) {
        this.academicClassLevelId = academicClassLevelId;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }
}
