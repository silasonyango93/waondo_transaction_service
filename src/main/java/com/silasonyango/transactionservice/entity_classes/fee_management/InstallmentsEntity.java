package com.silasonyango.transactionservice.entity_classes.fee_management;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "installments")
public class InstallmentsEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InstallmentId")
    private int installmentId;

    @Column(name = "StudentId")
    private int studentId;

    @Column(name = "InstallmentAmount")
    private int installmentAmount;

    @Column(name = "InstallmentDate")
    private String installmentDate;

    @Column(name = "IsCarryForward")
    private int isCarryForward;

    @Column(name = "SessionLogId")
    private int sessionLogId;

    @Column(name = "UserSessionActivityId")
    private int userSessionActivityId;

    @Column(name = "InstallmentYear")
    private String installmentYear;

    @Column(name = "IsAdminInstallment")
    private int isAdminInstallment;

    public InstallmentsEntity() {}

    public InstallmentsEntity(int studentId, int installmentAmount, String installmentDate, int isCarryForward, int sessionLogId, int userSessionActivityId, String installmentYear, int isAdminInstallment) {
        this.studentId = studentId;
        this.installmentAmount = installmentAmount;
        this.installmentDate = installmentDate;
        this.isCarryForward = isCarryForward;
        this.sessionLogId = sessionLogId;
        this.userSessionActivityId = userSessionActivityId;
        this.installmentYear = installmentYear;
        this.isAdminInstallment = isAdminInstallment;
    }

    public int getInstallmentId() {
        return installmentId;
    }

    public void setInstallmentId(int installmentId) {
        this.installmentId = installmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(int installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public String getInstallmentDate() {
        return installmentDate;
    }

    public void setInstallmentDate(String installmentDate) {
        this.installmentDate = installmentDate;
    }

    public int getIsCarryForward() {
        return isCarryForward;
    }

    public void setIsCarryForward(int isCarryForward) {
        this.isCarryForward = isCarryForward;
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

    public String getInstallmentYear() {
        return installmentYear;
    }

    public void setInstallmentYear(String installmentYear) {
        this.installmentYear = installmentYear;
    }

    public int getIsAdminInstallment() {
        return isAdminInstallment;
    }

    public void setIsAdminInstallment(int isAdminInstallment) {
        this.isAdminInstallment = isAdminInstallment;
    }
}
