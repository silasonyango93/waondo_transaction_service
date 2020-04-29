package com.silasonyango.transactionservice.entity_classes.fee_management;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "fee_statements")
public class FeeStatementEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FeeStatementId")
    private int feeStatementId;

    @Column(name = "StudentId")
    private int studentId;

    @Column(name = "CurrentYearTotal")
    private int currentYearTotal;

    @Column(name = "AlternateTotal")
    private int alternateTotal;

    @Column(name = "CurrentTermBalance")
    private int currentTermBalance;

    @Column(name = "AnnualBalance")
    private int annualBalance;

    @Column(name = "StudentWorth")
    private int studentWorth;

    public FeeStatementEntity() {

    }

    public FeeStatementEntity(int studentId,int currentYearTotal,int alternateTotal,int currentTermBalance,int annualBalance,int studentWorth) {
        this.studentId = studentId;
        this.currentYearTotal = currentYearTotal;
        this.currentTermBalance = currentTermBalance;
        this.annualBalance = annualBalance;
        this.studentWorth = studentWorth;
        this.alternateTotal = alternateTotal;

    }

    public int getFeeStatementId() {
        return feeStatementId;
    }

    public void setFeeStatementId(int feeStatementId) {
        this.feeStatementId = feeStatementId;
    }

    public int getCurrentYearTotal() {
        return currentYearTotal;
    }

    public void setCurrentYearTotal(int currentYearTotal) {
        this.currentYearTotal = currentYearTotal;
    }

    public int getCurrentTermBalance() {
        return currentTermBalance;
    }

    public void setCurrentTermBalance(int currentTermBalance) {
        this.currentTermBalance = currentTermBalance;
    }

    public int getAnnualBalance() {
        return annualBalance;
    }

    public void setAnnualBalance(int annualBalance) {
        this.annualBalance = annualBalance;
    }

    public int getStudentWorth() {
        return studentWorth;
    }

    public void setStudentWorth(int studentWorth) {
        this.studentWorth = studentWorth;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getAlternateTotal() {
        return alternateTotal;
    }

    public void setAlternateTotal(int alternateTotal) {
        this.alternateTotal = alternateTotal;
    }
}
