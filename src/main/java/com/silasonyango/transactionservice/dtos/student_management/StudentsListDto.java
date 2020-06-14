package com.silasonyango.transactionservice.dtos.student_management;

public class StudentsListDto {
    private int studentId;
    private String admissionNumber;
    private String studentName;
    private String gender;
    private String classDetails;
    private String residenceDetails;

    public StudentsListDto() {
    }

    public StudentsListDto(int studentId, String admissionNumber, String studentName, String gender, String classDetails, String residenceDetails) {
        this.studentId = studentId;
        this.admissionNumber = admissionNumber;
        this.studentName = studentName;
        this.gender = gender;
        this.classDetails = classDetails;
        this.residenceDetails = residenceDetails;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClassDetails() {
        return classDetails;
    }

    public void setClassDetails(String classDetails) {
        this.classDetails = classDetails;
    }

    public String getResidenceDetails() {
        return residenceDetails;
    }

    public void setResidenceDetails(String residenceDetails) {
        this.residenceDetails = residenceDetails;
    }
}
