package com.silasonyango.transactionservice.dtos.fee_management.fee_structure;

import org.apache.tomcat.util.codec.binary.Base64;

import java.util.List;

public class ClassFeeStructureModel {
    private int feeStructureId;
    private int userId;
    private String feeStructureDescription;
    private String dateCreated;
    private int isCurrentFeeStructure;
    private int isProspect;
    private int classFeeStructureId;
    private int academicClassLevelId;
    private String academicClassLevelName;
    private int hierachyCode;
    private int isAdminClassLevel;
    private List<ClassFeeStructureBreakDownModel> classFeeStructureBreakDown;
    private List<ClassFeeStructureComponentModel> classFeeStructureComponents;

    public ClassFeeStructureModel() {
    }

    public ClassFeeStructureModel(int feeStructureId, int userId, String feeStructureDescription, String dateCreated, int isCurrentFeeStructure, int isProspect, int classFeeStructureId, int academicClassLevelId, String academicClassLevelName, int hierachyCode, int isAdminClassLevel, List<ClassFeeStructureBreakDownModel> classFeeStructureBreakDown, List<ClassFeeStructureComponentModel> classFeeStructureComponents) {
        this.feeStructureId = feeStructureId;
        this.userId = userId;
        this.feeStructureDescription = feeStructureDescription;
        this.dateCreated = dateCreated;
        this.isCurrentFeeStructure = isCurrentFeeStructure;
        this.isProspect = isProspect;
        this.classFeeStructureId = classFeeStructureId;
        this.academicClassLevelId = academicClassLevelId;
        this.academicClassLevelName = academicClassLevelName;
        this.hierachyCode = hierachyCode;
        this.isAdminClassLevel = isAdminClassLevel;
        this.classFeeStructureBreakDown = classFeeStructureBreakDown;
        this.classFeeStructureComponents = classFeeStructureComponents;
    }

    public ClassFeeStructureModel(int feeStructureId, int userId, String feeStructureDescription, String dateCreated, int isCurrentFeeStructure, int isProspect, int classFeeStructureId, int academicClassLevelId, String academicClassLevelName, int hierachyCode, int isAdminClassLevel) {
        this.feeStructureId = feeStructureId;
        this.userId = userId;
        this.feeStructureDescription = feeStructureDescription;
        this.dateCreated = dateCreated;
        this.isCurrentFeeStructure = isCurrentFeeStructure;
        this.isProspect = isProspect;
        this.classFeeStructureId = classFeeStructureId;
        this.academicClassLevelId = academicClassLevelId;
        this.academicClassLevelName = academicClassLevelName;
        this.hierachyCode = hierachyCode;
        this.isAdminClassLevel = isAdminClassLevel;
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

    public List<ClassFeeStructureBreakDownModel> getClassFeeStructureBreakDown() {
        return classFeeStructureBreakDown;
    }

    public void setClassFeeStructureBreakDown(List<ClassFeeStructureBreakDownModel> classFeeStructureBreakDown) {
        this.classFeeStructureBreakDown = classFeeStructureBreakDown;
    }

    public List<ClassFeeStructureComponentModel> getClassFeeStructureComponents() {
        return classFeeStructureComponents;
    }

    public void setClassFeeStructureComponents(List<ClassFeeStructureComponentModel> classFeeStructureComponents) {
        this.classFeeStructureComponents = classFeeStructureComponents;
    }
}
