package com.silasonyango.transactionservice.entity_classes.student_management;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "student_registration")
public class StudentRegistrationEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StudentRegistrationId")
    private int studentRegistrationId;

    @Column(name = "SessionLogId")
    private int sessionLogId;

    @Column(name = "UserSessionActivityId")
    private int userSessionActivityId;

    @Column(name = "StudentId")
    private int studentId;

    @Column(name = "StudentRegistrationDate")
    private String studentRegistrationDate;

    public StudentRegistrationEntity() {}

    public StudentRegistrationEntity(int sessionLogId, int userSessionActivityId, int studentId, String studentRegistrationDate) {
        this.sessionLogId = sessionLogId;
        this.userSessionActivityId = userSessionActivityId;
        this.studentId = studentId;
        this.studentRegistrationDate = studentRegistrationDate;
    }

    public int getStudentRegistrationId() {
        return studentRegistrationId;
    }

    public void setStudentRegistrationId(int studentRegistrationId) {
        this.studentRegistrationId = studentRegistrationId;
    }

    public int getSessionLogId() {
        return sessionLogId;
    }

    public void setSessionLogId(int sessionLogId) {
        this.sessionLogId = sessionLogId;
    }

    public int getUserSessionActivityId() {
        return userSessionActivityId;
    }

    public void setUserSessionActivityId(int userSessionActivityId) {
        this.userSessionActivityId = userSessionActivityId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentRegistrationDate() {
        return studentRegistrationDate;
    }

    public void setStudentRegistrationDate(String studentRegistrationDate) {
        this.studentRegistrationDate = studentRegistrationDate;
    }
}
