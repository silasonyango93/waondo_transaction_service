package com.silasonyango.transactionservice.dtos.fee_management;

public class FeeComponentsResponseDto {
    private String feeComponentDescription;
    private double componentFeeAmount;

    public FeeComponentsResponseDto() {}

    public FeeComponentsResponseDto(String feeComponentDescription,double componentFeeAmount) {
        this.feeComponentDescription = feeComponentDescription;
        this.componentFeeAmount = componentFeeAmount;
    }

    public String getFeeComponentDescription() {
        return feeComponentDescription;
    }

    public void setFeeComponentDescription(String feeComponentDescription) {
        this.feeComponentDescription = feeComponentDescription;
    }

    public double getComponentFeeAmount() {
        return componentFeeAmount;
    }

    public void setComponentFeeAmount(double componentFeeAmount) {
        this.componentFeeAmount = componentFeeAmount;
    }
}
