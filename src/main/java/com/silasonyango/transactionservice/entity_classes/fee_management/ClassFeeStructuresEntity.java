package com.silasonyango.transactionservice.entity_classes.fee_management;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "class_fee_structures")
public class ClassFeeStructuresEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClassFeeStructureId")
    private int classFeeStructureId;

    @Column(name = "FeeStructureId")
    private int feeStructureId;

    @Column(name = "AcademicClassLevelId")
    private int academicClassLevelId;

    public ClassFeeStructuresEntity() {
    }

    public ClassFeeStructuresEntity(int feeStructureId, int academicClassLevelId) {
        this.feeStructureId = feeStructureId;
        this.academicClassLevelId = academicClassLevelId;
    }

    public int getClassFeeStructureId() {
        return classFeeStructureId;
    }

    public void setClassFeeStructureId(int classFeeStructureId) {
        this.classFeeStructureId = classFeeStructureId;
    }

    public int getFeeStructureId() {
        return feeStructureId;
    }

    public void setFeeStructureId(int feeStructureId) {
        this.feeStructureId = feeStructureId;
    }

    public int getAcademicClassLevelId() {
        return academicClassLevelId;
    }

    public void setAcademicClassLevelId(int academicClassLevelId) {
        this.academicClassLevelId = academicClassLevelId;
    }
}
