package com.silasonyango.transactionservice.entity_classes.student_residence;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "residence_swap")
public class ResidenceSwapEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ResidenceSwapId")
    private int residenceSwapId;

    @Column(name = "ResidenceSwapTypeId")
    private int residenceSwapTypeId;

    @Column(name = "SessionLogId")
    private int sessionLogId;

    @Column(name = "StudentId")
    private int studentId;

    @Column(name = "ResidenceSwapDate")
    private String residenceSwapDate;

    public int getResidenceSwapId() {
        return residenceSwapId;
    }

    public void setResidenceSwapId(int residenceSwapId) {
        this.residenceSwapId = residenceSwapId;
    }

    public int getResidenceSwapTypeId() {
        return residenceSwapTypeId;
    }

    public void setResidenceSwapTypeId(int residenceSwapTypeId) {
        this.residenceSwapTypeId = residenceSwapTypeId;
    }

    public int getSessionLogId() {
        return sessionLogId;
    }

    public void setSessionLogId(int sessionLogId) {
        this.sessionLogId = sessionLogId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getResidenceSwapDate() {
        return residenceSwapDate;
    }

    public void setResidenceSwapDate(String residenceSwapDate) {
        this.residenceSwapDate = residenceSwapDate;
    }
}
