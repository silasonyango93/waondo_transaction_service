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
}
