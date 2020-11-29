package com.silasonyango.transactionservice.dtos.fee_management.fee_structure;

public class EncodedFeeStructureIdClassFeeStructureRequest {
    private String encodedFeeStructureId;

    public EncodedFeeStructureIdClassFeeStructureRequest() {
    }

    public EncodedFeeStructureIdClassFeeStructureRequest(String encodedFeeStructureId) {
        this.encodedFeeStructureId = encodedFeeStructureId;
    }

    public String getEncodedFeeStructureId() {
        return encodedFeeStructureId;
    }

    public void setEncodedFeeStructureId(String encodedFeeStructureId) {
        this.encodedFeeStructureId = encodedFeeStructureId;
    }
}
