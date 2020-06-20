package com.silasonyango.transactionservice.dtos.student_management;

public class StudentRequestByAdmissionNoDto {
    private String admissionNumber;

    public StudentRequestByAdmissionNoDto() {
    }

    public StudentRequestByAdmissionNoDto(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }
}
