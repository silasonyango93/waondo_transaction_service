package com.silasonyango.transactionservice.services.retrofit.waondo_node.academic_class_management;

import com.google.gson.annotations.SerializedName;

public class AcademicClassResponseDto {
    @SerializedName("ClassId")
    private int classId;

    @SerializedName("LotId")
    private int lotId;

    @SerializedName("ClassStreamId")
    private int classStreamId;

    @SerializedName("RegisteredDate")
    private String registeredDate;

    @SerializedName("IsAdminClass")
    private int isAdminClass;

    @SerializedName("LotDescriptionId")
    private int lotDescriptionId;

    @SerializedName("AcademicClassLevelId")
    private int academicClassLevelId;

    @SerializedName("IsAdminLot")
    private int isAdminLot;

    @SerializedName("hasCompletedSchool")
    private int hasCompletedSchool;

    @SerializedName("AcademicClassLevelName")
    private String academicClassLevelName;

    @SerializedName("HierachyCode")
    private int hierachyCode;

    @SerializedName("IsAdminClassLevel")
    private int isAdminClassLevel;

    @SerializedName("LotDescription")
    private String lotDescription;

    @SerializedName("IsAdminLotDescription")
    private int isAdminLotDescription;

    @SerializedName("ClassStreamName")
    private String classStreamName;

    @SerializedName("IsAdminClassStream")
    private int isAdminClassStream;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public int getClassStreamId() {
        return classStreamId;
    }

    public void setClassStreamId(int classStreamId) {
        this.classStreamId = classStreamId;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public int getIsAdminClass() {
        return isAdminClass;
    }

    public void setIsAdminClass(int isAdminClass) {
        this.isAdminClass = isAdminClass;
    }

    public int getLotDescriptionId() {
        return lotDescriptionId;
    }

    public void setLotDescriptionId(int lotDescriptionId) {
        this.lotDescriptionId = lotDescriptionId;
    }

    public int getAcademicClassLevelId() {
        return academicClassLevelId;
    }

    public void setAcademicClassLevelId(int academicClassLevelId) {
        this.academicClassLevelId = academicClassLevelId;
    }

    public int getIsAdminLot() {
        return isAdminLot;
    }

    public void setIsAdminLot(int isAdminLot) {
        this.isAdminLot = isAdminLot;
    }

    public int getHasCompletedSchool() {
        return hasCompletedSchool;
    }

    public void setHasCompletedSchool(int hasCompletedSchool) {
        this.hasCompletedSchool = hasCompletedSchool;
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

    public String getLotDescription() {
        return lotDescription;
    }

    public void setLotDescription(String lotDescription) {
        this.lotDescription = lotDescription;
    }

    public int getIsAdminLotDescription() {
        return isAdminLotDescription;
    }

    public void setIsAdminLotDescription(int isAdminLotDescription) {
        this.isAdminLotDescription = isAdminLotDescription;
    }

    public String getClassStreamName() {
        return classStreamName;
    }

    public void setClassStreamName(String classStreamName) {
        this.classStreamName = classStreamName;
    }

    public int getIsAdminClassStream() {
        return isAdminClassStream;
    }

    public void setIsAdminClassStream(int isAdminClassStream) {
        this.isAdminClassStream = isAdminClassStream;
    }
}
