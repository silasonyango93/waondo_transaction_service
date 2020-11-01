package com.silasonyango.transactionservice.dtos.fee_management.fee_structure;

public class FeeStructureCreationRequestModel {
    private int userId;
    private String feeStructureDescription;

    public FeeStructureCreationRequestModel() {
    }

    public FeeStructureCreationRequestModel(int userId, String feeStructureDescription) {
        this.userId = userId;
        this.feeStructureDescription = feeStructureDescription;
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
}
