package com.silasonyango.transactionservice.entity_classes.student_residence;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "residence_swap_type")
public class ResidenceSwapTypeEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ResidenceSwapTypeId")
    private int residenceSwapTypeId;

    @Column(name = "ResidenceSwapTypeDescription")
    private String residenceSwapTypeDescription;

    @Column(name = "ResidenceSwapTypeCode")
    private int residenceSwapTypeCode;

    public ResidenceSwapTypeEntity(String residenceSwapTypeDescription, int residenceSwapTypeCode) {
        this.residenceSwapTypeDescription = residenceSwapTypeDescription;
        this.residenceSwapTypeCode = residenceSwapTypeCode;
    }

    public int getResidenceSwapTypeId() {
        return residenceSwapTypeId;
    }

    public void setResidenceSwapTypeId(int residenceSwapTypeId) {
        this.residenceSwapTypeId = residenceSwapTypeId;
    }

    public String getResidenceSwapTypeDescription() {
        return residenceSwapTypeDescription;
    }

    public void setResidenceSwapTypeDescription(String residenceSwapTypeDescription) {
        this.residenceSwapTypeDescription = residenceSwapTypeDescription;
    }

    public int getResidenceSwapTypeCode() {
        return residenceSwapTypeCode;
    }

    public void setResidenceSwapTypeCode(int residenceSwapTypeCode) {
        this.residenceSwapTypeCode = residenceSwapTypeCode;
    }
}
