package com.silasonyango.transactionservice.ingestor.models;

import com.google.gson.annotations.SerializedName;

public class IngStudentModel {
    @SerializedName("StudentsId")
    private int studentsId;

    @SerializedName("AdmissionNo")
    private String admissionNo;

    @SerializedName("StudentName")
    private String studentName;

    @SerializedName("StudentGender")
    private String studentGender;

    @SerializedName("StudentType")
    private String studentType;

    @SerializedName("ClassId")
    private int classId;

    @SerializedName("CompletedSchool")
    private int completedSchool;

    private int genderId;

    public int getStudentsId() {
        return studentsId;
    }

    public void setStudentsId(int studentsId) {
        this.studentsId = studentsId;
    }

    public String getAdmissionNo() {
        return admissionNo;
    }

    public void setAdmissionNo(String admissionNo) {
        this.admissionNo = admissionNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getCompletedSchool() {
        return completedSchool;
    }

    public void setCompletedSchool(int completedSchool) {
        this.completedSchool = completedSchool;
    }
}
