package com.silasonyango.transactionservice.entity_classes.student_management;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "students")
public class StudentEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StudentsId")
    private int studentId;

    @Column(name = "AdmissionNo")
    private String admissionNo;

    @Column(name = "StudentName")
    private String studentName;

    @Column(name = "GenderId")
    private int genderId;

    @Column(name = "StudentDOB")
    private String studentDob;

    @Column(name = "StudentTypeId")
    private int studentTypeId;

    @Column(name = "ClassId")
    private int classId;

    @Column(name = "AdmissionDate")
    private String admissionDate;

    @Column(name = "ProfPicName")
    private String profPicName;

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
}
