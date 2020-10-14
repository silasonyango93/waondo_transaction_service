package com.silasonyango.transactionservice.dtos.student_management;

public class StudentRequestByStudentIdDto {
    private int studentId;
    private String admissionNo;

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
}
