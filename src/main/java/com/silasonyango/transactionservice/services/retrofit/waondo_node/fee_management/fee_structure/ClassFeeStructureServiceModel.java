package com.silasonyango.transactionservice.services.retrofit.waondo_node.fee_management.fee_structure;

import com.google.gson.annotations.SerializedName;

public class ClassFeeStructureServiceModel {
    @SerializedName("FeeStructureId")
    private int feeStructureId;

    @SerializedName("UserId")
    private int userId;

    @SerializedName("FeeStructureDescription")
    private String feeStructureDescription;

    @SerializedName("DateCreated")
    private String dateCreated;

    @SerializedName("IsCurrentFeeStructure")
    private int isCurrentFeeStructure;

    @SerializedName("IsProspect")
    private int isProspect;

    @SerializedName("ClassFeeStructureId")
    private int classFeeStructureId;

    @SerializedName("AcademicClassLevelId")
    private int academicClassLevelId;

    @SerializedName("AcademicClassLevelName")
    private String academicClassLevelName;

    @SerializedName("HierachyCode")
    private int hierachyCode;

    @SerializedName("IsAdminClassLevel")
    private int isAdminClassLevel;

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

    public int getClassFeeStructureId() {
        return classFeeStructureId;
    }

    public void setClassFeeStructureId(int classFeeStructureId) {
        this.classFeeStructureId = classFeeStructureId;
    }

    public int getAcademicClassLevelId() {
        return academicClassLevelId;
    }

    public void setAcademicClassLevelId(int academicClassLevelId) {
        this.academicClassLevelId = academicClassLevelId;
    }

    public String getAcademicClassLevelName() {
        return academicClassLevelName;
    }

    public void setAcademicClassLevelName(String academicClassLevelName) {
        this.academicClassLevelName = academicClassLevelName;
    }

    public int getHierachyCode() {
        return hierachyCode;
    }

    public void setHierachyCode(int hierachyCode) {
        this.hierachyCode = hierachyCode;
    }

    public int getIsAdminClassLevel() {
        return isAdminClassLevel;
    }

    public void setIsAdminClassLevel(int isAdminClassLevel) {
        this.isAdminClassLevel = isAdminClassLevel;
    }
}
