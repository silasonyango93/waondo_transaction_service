package com.silasonyango.transactionservice.dtos.fee_management.fee_structure;

public class FeeStructureDuplicateRequestDto {
    private int feeStructureId;
    private int userId;
    private String duplicateFeeStructureName;

    public FeeStructureDuplicateRequestDto() {
    }

    public FeeStructureDuplicateRequestDto(int feeStructureId, int userId, String duplicateFeeStructureName) {
        this.feeStructureId = feeStructureId;
        this.userId = userId;
        this.duplicateFeeStructureName = duplicateFeeStructureName;
    }

    public int getFeeStructureId() {
        return feeStructureId;
    }

    public void setFeeStructureId(int feeStructureId) {
        this.feeStructureId = feeStructureId;
    }

    public String getDuplicateFeeStructureName() {
        return duplicateFeeStructureName;
    }

    public void setDuplicateFeeStructureName(String duplicateFeeStructureName) {
        this.duplicateFeeStructureName = duplicateFeeStructureName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
