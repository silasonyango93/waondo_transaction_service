package com.silasonyango.transactionservice.entity_classes.fee_management;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "fee_components")
public class FeeComponentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FeeComponentId")
    private int feeComponentId;

    @Column(name = "FeeComponentDescription")
    private String feeComponentDescription;

    public FeeComponentsEntity() {
    }

    public FeeComponentsEntity(String feeComponentDescription) {
        this.feeComponentDescription = feeComponentDescription;
    }

    public int getFeeComponentId() {
        return feeComponentId;
    }

    public void setFeeComponentId(int feeComponentId) {
        this.feeComponentId = feeComponentId;
    }

    public String getFeeComponentDescription() {
        return feeComponentDescription;
    }

    public void setFeeComponentDescription(String feeComponentDescription) {
        this.feeComponentDescription = feeComponentDescription;
    }
}
