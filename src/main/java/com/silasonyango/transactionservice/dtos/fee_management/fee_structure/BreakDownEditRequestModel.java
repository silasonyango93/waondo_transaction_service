package com.silasonyango.transactionservice.dtos.fee_management.fee_structure;

public class BreakDownEditRequestModel {
    private int classFeeStructureBreakDownId;
    private double feeAmount;

    public int getClassFeeStructureBreakDownId() {
        return classFeeStructureBreakDownId;
    }

    public void setClassFeeStructureBreakDownId(int classFeeStructureBreakDownId) {
        this.classFeeStructureBreakDownId = classFeeStructureBreakDownId;
    }

    public double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(double feeAmount) {
        this.feeAmount = feeAmount;
    }
}
