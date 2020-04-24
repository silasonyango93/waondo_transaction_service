package com.silasonyango.transactionservice.entity_classes.fee_management;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "fee_statements")
public class FeeStatementEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FeeStatementId")
    private int feeStatementId;

    @Column(name = "AdmissionNo")
    private String admissionNo;

    @Column(name = "LunchScheme")
    private int lunchScheme;

    @Column(name = "PE")
    private int PE;

    @Column(name = "EW")
    private int EW;

    @Column(name = "LT")
    private int LT;

    @Column(name = "RMI")
    private int RMI;

    @Column(name = "Administration")
    private int administration;

    @Column(name = "Activity")
    private int activity;

    @Column(name = "CurrentYearTotal")
    private int currentYearTotal;

    @Column(name = "CurrentTermBalance")
    private int currentTermBalance;

    @Column(name = "AnnualBalance")
    private int annualBalance;

    public FeeStatementEntity() {

    }

    public FeeStatementEntity(String admissionNo,int lunchScheme,int PE,int EW,int LT,int RMI,int administration,int activity,int currentYearTotal,int currentTermBalance,int annualBalance) {
        this.admissionNo = admissionNo;
        this.lunchScheme = lunchScheme;
        this.PE = PE;
        this.EW = EW;
        this.LT = LT;
        this.RMI = RMI;
        this.administration = administration;
        this.activity = activity;
        this.currentYearTotal = currentYearTotal;
        this.currentTermBalance = currentTermBalance;
        this.annualBalance = annualBalance;

    }

    public int getFeeStatementId() {
        return feeStatementId;
    }

    public void setFeeStatementId(int feeStatementId) {
        this.feeStatementId = feeStatementId;
    }

    public String getAdmissionNo() {
        return admissionNo;
    }

    public void setAdmissionNo(String admissionNo) {
        this.admissionNo = admissionNo;
    }

    public int getLunchScheme() {
        return lunchScheme;
    }

    public void setLunchScheme(int lunchScheme) {
        this.lunchScheme = lunchScheme;
    }

    public int getPE() {
        return PE;
    }

    public void setPE(int PE) {
        this.PE = PE;
    }

    public int getEW() {
        return EW;
    }

    public void setEW(int EW) {
        this.EW = EW;
    }

    public int getLT() {
        return LT;
    }

    public void setLT(int LT) {
        this.LT = LT;
    }

    public int getRMI() {
        return RMI;
    }

    public void setRMI(int RMI) {
        this.RMI = RMI;
    }

    public int getAdministration() {
        return administration;
    }

    public void setAdministration(int administration) {
        this.administration = administration;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
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
}
