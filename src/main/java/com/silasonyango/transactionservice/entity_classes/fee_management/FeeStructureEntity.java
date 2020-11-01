package com.silasonyango.transactionservice.entity_classes.fee_management;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "fee_structures")
public class FeeStructureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FeeStructureId")
    private int feeStructureId;

    @Column(name = "UserId")
    private int userId;

    @Column(name = "FeeStructureDescription")
    private String feeStructureDescription;

    @Column(name = "DateCreated")
    private String dateCreated;

    @Column(name = "IsCurrentFeeStructure")
    private int isCurrentFeeStructure;

    @Column(name = "IsProspect")
    private int isProspect;

    public FeeStructureEntity(int userId, String feeStructureDescription, String dateCreated, int isCurrentFeeStructure, int isProspect) {
        this.userId = userId;
        this.feeStructureDescription = feeStructureDescription;
        this.dateCreated = dateCreated;
        this.isCurrentFeeStructure = isCurrentFeeStructure;
        this.isProspect = isProspect;
    }

    public int getFeeStructureId() {
        return feeStructureId;
    }

    public void setFeeStructureId(int feeStructureId) {
        this.feeStructureId = feeStructureId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFeeStructureDescription() {
        return feeStructureDescription;
    }

    public void setFeeStructureDescription(String feeStructureDescription) {
        this.feeStructureDescription = feeStructureDescription;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getIsCurrentFeeStructure() {
        return isCurrentFeeStructure;
    }

    public void setIsCurrentFeeStructure(int isCurrentFeeStructure) {
        this.isCurrentFeeStructure = isCurrentFeeStructure;
    }

    public int getIsProspect() {
        return isProspect;
    }

    public void setIsProspect(int isProspect) {
        this.isProspect = isProspect;
    }
}
