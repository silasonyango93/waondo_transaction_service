package com.silasonyango.transactionservice.entity_classes.academic_classes;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "classes")
public class ClassesEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClassId")
    private int classId;

    @Column(name = "LotId")
    private int lotId;

    @Column(name = "ClassStreamId")
    private int classStreamId;

    @Column(name = "RegisteredDate")
    private String RegisteredDate;

    @Column(name = "IsAdminClass")
    private int IsAdminClass;

    public ClassesEntity() {
    }

    public ClassesEntity(int lotId, int classStreamId, String registeredDate, int isAdminClass) {
        this.lotId = lotId;
        this.classStreamId = classStreamId;
        RegisteredDate = registeredDate;
        IsAdminClass = isAdminClass;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public int getClassStreamId() {
        return classStreamId;
    }

    public void setClassStreamId(int classStreamId) {
        this.classStreamId = classStreamId;
    }

    public String getRegisteredDate() {
        return RegisteredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        RegisteredDate = registeredDate;
    }

    public int getIsAdminClass() {
        return IsAdminClass;
    }

    public void setIsAdminClass(int isAdminClass) {
        IsAdminClass = isAdminClass;
    }
}
