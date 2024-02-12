package com.silasonyango.transactionservice.dtos.student_management;

public class StudentRegistrationDto {
    private int studentId;
    private String admissionNo;
    private String studentName;
    private int genderCode;
    private String studentDob;
    private int studentResidenceCode;
    private int classId;
    private String admissionDate;
    private String profPicName;
    private int registrationSessionId;
    private String parentPhoneNumber;

    public StudentRegistrationDto(String admissionNo, String studentName, int genderCode, String studentDob, int studentResidenceCode, int classId, String admissionDate, String profPicName, int registrationSessionId, String parentPhoneNumber) {
        this.admissionNo = admissionNo;
        this.studentName = studentName;
        this.genderCode = genderCode;
        this.studentDob = studentDob;
        this.studentResidenceCode = studentResidenceCode;
        this.classId = classId;
        this.admissionDate = admissionDate;
        this.profPicName = profPicName;
        this.registrationSessionId = registrationSessionId;
        this.parentPhoneNumber = parentPhoneNumber;
    }

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

    public int getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(int genderCode) {
        this.genderCode = genderCode;
    }

    public String getStudentDob() {
        return studentDob;
    }

    public void setStudentDob(String studentDob) {
        this.studentDob = studentDob;
    }

    public int getStudentResidenceCode() {
        return studentResidenceCode;
    }

    public void setStudentResidenceCode(int studentResidenceCode) {
        this.studentResidenceCode = studentResidenceCode;
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

    public int getRegistrationSessionId() {
        return registrationSessionId;
    }

    public void setRegistrationSessionId(int registrationSessionId) {
        this.registrationSessionId = registrationSessionId;
    }

    public String getParentPhoneNumber() {
        return parentPhoneNumber;
    }

    public void setParentPhoneNumber(String parentPhoneNumber) {
        this.parentPhoneNumber = parentPhoneNumber;
    }
}
