package com.silasonyango.transactionservice.dtos.student_management;

public class StudentPersonalDetailsResponseDto {
    private boolean isStudentDetailsAvailable;
    private int studentId;
    private String admissionNumber;
    private String studentName;
    private int genderCode;
    private String dateOfBirth;

    public StudentPersonalDetailsResponseDto() {
    }

    public StudentPersonalDetailsResponseDto(boolean isStudentDetailsAvailable,int studentId, String admissionNumber, String studentName, int genderCode, String dateOfBirth) {
        this.isStudentDetailsAvailable = isStudentDetailsAvailable;
        this.admissionNumber = admissionNumber;
        this.studentName = studentName;
        this.genderCode = genderCode;
        this.dateOfBirth = dateOfBirth;
        this.studentId = studentId;
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

    public int getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(int genderCode) {
        this.genderCode = genderCode;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isStudentDetailsAvailable() {
        return isStudentDetailsAvailable;
    }

    public void setStudentDetailsAvailable(boolean studentDetailsAvailable) {
        isStudentDetailsAvailable = studentDetailsAvailable;
    }
}
