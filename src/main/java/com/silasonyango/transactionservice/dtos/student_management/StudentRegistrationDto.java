package com.silasonyango.transactionservice.dtos.student_management;

public class StudentRegistrationDto {
    private int studentId;
    private String admissionNo;
    private String studentName;
    private int genderId;
    private String studentDob;
    private int studentTypeId;
    private int classId;
    private String admissionDate;
    private String profPicName;
    private int registrationSessionId;

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

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public String getStudentDob() {
        return studentDob;
    }

    public void setStudentDob(String studentDob) {
        this.studentDob = studentDob;
    }

    public int getStudentTypeId() {
        return studentTypeId;
    }

    public void setStudentTypeId(int studentTypeId) {
        this.studentTypeId = studentTypeId;
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
}
