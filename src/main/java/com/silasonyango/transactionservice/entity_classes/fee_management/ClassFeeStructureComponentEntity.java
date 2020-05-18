package com.silasonyango.transactionservice.entity_classes.fee_management;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "class_fee_structure_components")
public class ClassFeeStructureComponentEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClassFeeStructureComponentId")
    private int classFeeStructureComponentId;

    @Column(name = "ClassFeeStructureId")
    private int classFeeStructureId;

    @Column(name = "FeeComponentId")
    private int feeComponentId;

    @Column(name = "FeeComponentRatio")
    private double feeComponentRatio;

    public ClassFeeStructureComponentEntity() {

    }

    public ClassFeeStructureComponentEntity(int classFeeStructureId, int feeComponentId,double feeComponentRatio) {
        this.classFeeStructureId = classFeeStructureId;
        this.feeComponentId = feeComponentId;
        this.feeComponentRatio = feeComponentRatio;
    }

    public int getClassFeeStructureComponentId() {
        return classFeeStructureComponentId;
    }

    public void setClassFeeStructureComponentId(int classFeeStructureComponentId) {
        this.classFeeStructureComponentId = classFeeStructureComponentId;
    }

    public int getClassFeeStructureId() {
        return classFeeStructureId;
    }

    public void setClassFeeStructureId(int classFeeStructureId) {
        this.classFeeStructureId = classFeeStructureId;
    }

    public int getFeeComponentId() {
        return feeComponentId;
    }

    public void setFeeComponentId(int feeComponentId) {
        this.feeComponentId = feeComponentId;
    }

    public double getFeeComponentRatio() {
        return feeComponentRatio;
    }

    public void setFeeComponentRatio(double feeComponentRatio) {
        this.feeComponentRatio = feeComponentRatio;
    }
}
