package com.silasonyango.transactionservice.services.retrofit.waondo_node.students;

import com.google.gson.annotations.SerializedName;

public class StudentsListResponseDto {
    @SerializedName("StudentId")
    private int studentId;

    @SerializedName("AdmissionNo")
    private String admissionNo;

    @SerializedName("StudentName")
    private String studentName;

    @SerializedName("StudentResidenceId")
    private int studentResidenceId;

    @SerializedName("GenderId")
    private int genderId;

    @SerializedName("StudentDOB")
    private String studentDOB;

    @SerializedName("ClassId")
    private int classId;

    @SerializedName("AdmissionDate")
    private String admissionDate;

    @SerializedName("ProfPicName")
    private String profPicName;

    @SerializedName("IsAnAdminStudent")
    private int isAnAdminStudent;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

    public int getStudentResidenceId() {
        return studentResidenceId;
    }

    public void setStudentResidenceId(int studentResidenceId) {
        this.studentResidenceId = studentResidenceId;
    }

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public String getStudentDOB() {
        return studentDOB;
    }

    public void setStudentDOB(String studentDOB) {
        this.studentDOB = studentDOB;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getProfPicName() {
        return profPicName;
    }

    public void setProfPicName(String profPicName) {
        this.profPicName = profPicName;
    }

    public int getIsAnAdminStudent() {
        return isAnAdminStudent;
    }

    public void setIsAnAdminStudent(int isAnAdminStudent) {
        this.isAnAdminStudent = isAnAdminStudent;
    }
}
