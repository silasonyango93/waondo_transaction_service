package com.silasonyango.transactionservice.entity_classes.fee_management;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "fee_corrections")
public class FeeCorrectionsEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FeeCorrectionId")
    private int feeCorrectionId;

    @Column(name = "SessionLogId")
    private int sessionLogId;

    @Column(name = "UserSessionActivityId")
    private int userSessionActivityId;

    @Column(name = "CorrectionDescriptionId")
    private int correctionDescriptionId;

    @Column(name = "StudentId")
    private int studentId;

    @Column(name = "PreviousTermBalance")
    private int previousTermBalance;

    @Column(name = "PreviousAnnualBalance")
    private int previousAnnualBalance;

    @Column(name = "PreviousTotal")
    private int previousTotal;

    @Column(name = "NextTermBalance")
    private int nextTermBalance;

    @Column(name = "NextAnnualBalance")
    private int nextAnnualBalance;

    @Column(name = "NextTotal")
    private int nextTotal;

    @Column(name = "CorrectionDate")
    private String correctionDate;

    @Column(name = "IsAdminFeeCorrection")
    private int isAdminFeeCorrection;

    public FeeCorrectionsEntity() {
    }

    public FeeCorrectionsEntity(int sessionLogId, int userSessionActivityId, int correctionDescriptionId, int studentId, int previousTermBalance, int previousAnnualBalance, int previousTotal, int nextTermBalance, int nextAnnualBalance, int nextTotal, String correctionDate, int isAdminFeeCorrection) {
        this.sessionLogId = sessionLogId;
        this.userSessionActivityId = userSessionActivityId;
        this.correctionDescriptionId = correctionDescriptionId;
        this.studentId = studentId;
        this.previousTermBalance = previousTermBalance;
        this.previousAnnualBalance = previousAnnualBalance;
        this.previousTotal = previousTotal;
        this.nextTermBalance = nextTermBalance;
        this.nextAnnualBalance = nextAnnualBalance;
        this.nextTotal = nextTotal;
        this.correctionDate = correctionDate;
        this.isAdminFeeCorrection = isAdminFeeCorrection;
    }

    public int getFeeCorrectionId() {
        return feeCorrectionId;
    }

    public void setFeeCorrectionId(int feeCorrectionId) {
        this.feeCorrectionId = feeCorrectionId;
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

    public int getCorrectionDescriptionId() {
        return correctionDescriptionId;
    }

    public void setCorrectionDescriptionId(int correctionDescriptionId) {
        this.correctionDescriptionId = correctionDescriptionId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getPreviousTermBalance() {
        return previousTermBalance;
    }

    public void setPreviousTermBalance(int previousTermBalance) {
        this.previousTermBalance = previousTermBalance;
    }

    public int getPreviousAnnualBalance() {
        return previousAnnualBalance;
    }

    public void setPreviousAnnualBalance(int previousAnnualBalance) {
        this.previousAnnualBalance = previousAnnualBalance;
    }

    public int getPreviousTotal() {
        return previousTotal;
    }

    public void setPreviousTotal(int previousTotal) {
        this.previousTotal = previousTotal;
    }

    public int getNextTermBalance() {
        return nextTermBalance;
    }

    public void setNextTermBalance(int nextTermBalance) {
        this.nextTermBalance = nextTermBalance;
    }

    public int getNextAnnualBalance() {
        return nextAnnualBalance;
    }

    public void setNextAnnualBalance(int nextAnnualBalance) {
        this.nextAnnualBalance = nextAnnualBalance;
    }

    public int getNextTotal() {
        return nextTotal;
    }

    public void setNextTotal(int nextTotal) {
        this.nextTotal = nextTotal;
    }

    public String getCorrectionDate() {
        return correctionDate;
    }

    public void setCorrectionDate(String correctionDate) {
        this.correctionDate = correctionDate;
    }

    public int getIsAdminFeeCorrection() {
        return isAdminFeeCorrection;
    }

    public void setIsAdminFeeCorrection(int isAdminFeeCorrection) {
        this.isAdminFeeCorrection = isAdminFeeCorrection;
    }
}
