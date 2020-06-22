package com.silasonyango.transactionservice.dtos.student_management;

public class StudentPersonalDetailsResponseDto {
    private boolean isStudentDetailsAvailable;
    private int studentId;
    private String admissionNumber;
    private String studentName;
    private String genderDetails;
    private String dateOfBirth;

    public StudentPersonalDetailsResponseDto() {
    }

    public StudentPersonalDetailsResponseDto(boolean isStudentDetailsAvailable,int studentId, String admissionNumber, String studentName, String genderDetails, String dateOfBirth) {
        this.isStudentDetailsAvailable = isStudentDetailsAvailable;
        this.admissionNumber = admissionNumber;
        this.studentName = studentName;
        this.genderDetails = genderDetails;
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

    public String getGenderDetails() {
        return genderDetails;
    }

    public void setGenderDetails(String genderDetails) {
        this.genderDetails = genderDetails;
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
