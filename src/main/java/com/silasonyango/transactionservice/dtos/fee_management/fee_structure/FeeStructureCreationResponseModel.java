package com.silasonyango.transactionservice.dtos.fee_management.fee_structure;

import org.apache.tomcat.util.codec.binary.Base64;

import java.util.List;

public class FeeStructureCreationResponseModel {
    private int feeStructureId;
    private int userId;
    private String feeStructureDescription;
    private String dateCreated;
    private int isCurrentFeeStructure;
    private int isProspectFeeStructure;
    private String encodedFeeStructureId;
    List<ClassFeeStructureModel> classFeeStructureModelList;

    public FeeStructureCreationResponseModel() {
    }

    public FeeStructureCreationResponseModel(int feeStructureId, int userId, String feeStructureDescription, String dateCreated, int isCurrentFeeStructure, int isProspectFeeStructure, List<ClassFeeStructureModel> classFeeStructureModelList) {
        this.feeStructureId = feeStructureId;
        this.userId = userId;
        this.feeStructureDescription = feeStructureDescription;
        this.dateCreated = dateCreated;
        this.isCurrentFeeStructure = isCurrentFeeStructure;
        this.isProspectFeeStructure = isProspectFeeStructure;
        this.classFeeStructureModelList = classFeeStructureModelList;
        byte[] bytesEncoded = Base64.encodeBase64(String.valueOf(feeStructureId).getBytes());
        this.encodedFeeStructureId = new String(bytesEncoded);
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

    public int getIsProspectFeeStructure() {
        return isProspectFeeStructure;
    }

    public void setIsProspectFeeStructure(int isProspectFeeStructure) {
        this.isProspectFeeStructure = isProspectFeeStructure;
    }

    public List<ClassFeeStructureModel> getClassFeeStructureModelList() {
        return classFeeStructureModelList;
    }

    public void setClassFeeStructureModelList(List<ClassFeeStructureModel> classFeeStructureModelList) {
        this.classFeeStructureModelList = classFeeStructureModelList;
    }

    public String getEncodedFeeStructureId() {
        return encodedFeeStructureId;
    }

    public void setEncodedFeeStructureId(String encodedFeeStructureId) {
        this.encodedFeeStructureId = encodedFeeStructureId;
    }
}
