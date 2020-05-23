package com.silasonyango.transactionservice.entity_classes.fee_management;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "transactions")
public class TransactionsEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TransactionId")
    private int transactionId;

    @Column(name = "SessionLogId")
    private int sessionLogId;

    @Column(name = "UserSessionActivityId")
    private int userSessionActivityId;

    @Column(name = "TransactionDescriptionId")
    private int transactionDescriptionId;

    @Column(name = "StudentId")
    private int studentId;

    @Column(name = "InstallmentId")
    private int installmentId;

    @Column(name = "CarryFowardId")
    private int carryFowardId;

    @Column(name = "FeeCorrectionId")
    private int feeCorrectionId;

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

    @Column(name = "TransactionDate")
    private String transactionDate;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
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

    public int getTransactionDescriptionId() {
        return transactionDescriptionId;
    }

    public void setTransactionDescriptionId(int transactionDescriptionId) {
        this.transactionDescriptionId = transactionDescriptionId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getInstallmentId() {
        return installmentId;
    }

    public void setInstallmentId(int installmentId) {
        this.installmentId = installmentId;
    }

    public int getCarryFowardId() {
        return carryFowardId;
    }

    public void setCarryFowardId(int carryFowardId) {
        this.carryFowardId = carryFowardId;
    }

    public int getFeeCorrectionId() {
        return feeCorrectionId;
    }

    public void setFeeCorrectionId(int feeCorrectionId) {
        this.feeCorrectionId = feeCorrectionId;
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

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
