package com.silasonyango.transactionservice.dtos.fee_management.fee_structure;

import com.google.gson.annotations.SerializedName;

public class ClassFeeStructureComponentModel {
    @SerializedName("ClassFeeStructureComponentId")
    private int classFeeStructureComponentId;

    @SerializedName("ClassFeeStructureId")
    private int classFeeStructureId;

    @SerializedName("FeeComponentId")
    private int feeComponentId;

    @SerializedName("FeeComponentRatio")
    private int feeComponentRatio;

    @SerializedName("FeeComponentDescription")
    private String feeComponentDescription;

    public ClassFeeStructureComponentModel() {
    }

    public ClassFeeStructureComponentModel(int classFeeStructureComponentId, int classFeeStructureId, int feeComponentId, int feeComponentRatio, String feeComponentDescription) {
        this.classFeeStructureComponentId = classFeeStructureComponentId;
        this.classFeeStructureId = classFeeStructureId;
        this.feeComponentId = feeComponentId;
        this.feeComponentRatio = feeComponentRatio;
        this.feeComponentDescription = feeComponentDescription;
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

    public int getFeeComponentRatio() {
        return feeComponentRatio;
    }

    public void setFeeComponentRatio(int feeComponentRatio) {
        this.feeComponentRatio = feeComponentRatio;
    }

    public String getFeeComponentDescription() {
        return feeComponentDescription;
    }

    public void setFeeComponentDescription(String feeComponentDescription) {
        this.feeComponentDescription = feeComponentDescription;
    }
}
