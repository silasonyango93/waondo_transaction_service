package com.silasonyango.transactionservice.dtos.fee_management.fee_structure;

public class FeeStructureRequestDto {
    private int feeStructureId;

    public FeeStructureRequestDto() {
    }

    public FeeStructureRequestDto(int feeStructureId) {
        this.feeStructureId = feeStructureId;
    }

    public int getFeeStructureId() {
        return feeStructureId;
    }

    public void setFeeStructureId(int feeStructureId) {
        this.feeStructureId = feeStructureId;
    }
}
