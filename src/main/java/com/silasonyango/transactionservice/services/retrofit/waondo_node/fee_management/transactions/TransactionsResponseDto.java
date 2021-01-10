package com.silasonyango.transactionservice.services.retrofit.waondo_node.fee_management.transactions;

import com.google.gson.annotations.SerializedName;
import com.silasonyango.transactionservice.common.config.TransactionDescriptionsConfig;

public class TransactionsResponseDto {
    @SerializedName("TransactionId")
    private int transactionId;

    @SerializedName("StudentId")
    private int studentId;

    @SerializedName("AdmissionNo")
    private String admissionNo;

    @SerializedName("AcademicClassLevelName")
    private String academicClassLevelName;

    @SerializedName("ClassStreamName")
    private String classStreamName;

    @SerializedName("TransactionDescription")
    private String transactionDescription;

    @SerializedName("PreviousTermBalance")
    private double previousTermBalance;

    @SerializedName("PreviousAnnualBalance")
    private double previousAnnualBalance;

    @SerializedName("PreviousTotal")
    private double previousTotal;

    @SerializedName("NextTermBalance")
    private double nextTermBalance;

    @SerializedName("NextAnnualBalance")
    private double nextAnnualBalance;

    @SerializedName("NextTotal")
    private double nextTotal;

    @SerializedName("TransactionDate")
    private String transactionDate;

    @SerializedName("Name")
    private String staff;

    @SerializedName("StudentName")
    private String studentName;

    @SerializedName("InstallmentAmount")
    private double installmentAmount;

    @SerializedName("CarryForwardAmount")
    private double carryForwardAmount;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public double getPreviousTermBalance() {
        return previousTermBalance;
    }

    public void setPreviousTermBalance(double previousTermBalance) {
        this.previousTermBalance = previousTermBalance;
    }

    public double getPreviousAnnualBalance() {
        return previousAnnualBalance;
    }

    public void setPreviousAnnualBalance(double previousAnnualBalance) {
        this.previousAnnualBalance = previousAnnualBalance;
    }

    public double getPreviousTotal() {
        return previousTotal;
    }

    public void setPreviousTotal(double previousTotal) {
        this.previousTotal = previousTotal;
    }

    public double getNextTermBalance() {
        return nextTermBalance;
    }

    public void setNextTermBalance(double nextTermBalance) {
        this.nextTermBalance = nextTermBalance;
    }

    public double getNextAnnualBalance() {
        return nextAnnualBalance;
    }

    public void setNextAnnualBalance(double nextAnnualBalance) {
        this.nextAnnualBalance = nextAnnualBalance;
    }

    public double getNextTotal() {
        return nextTotal;
    }

    public void setNextTotal(double nextTotal) {
        this.nextTotal = nextTotal;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(double installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public double getCarryForwardAmount() {
        return carryForwardAmount;
    }

    public void setCarryForwardAmount(double carryForwardAmount) {
        this.carryForwardAmount = carryForwardAmount;
    }

    public String getAdmissionNo() {
        return admissionNo;
    }

    public void setAdmissionNo(String admissionNo) {
        this.admissionNo = admissionNo;
    }

    public String getAcademicClassLevelName() {
        return academicClassLevelName;
    }

    public void setAcademicClassLevelName(String academicClassLevelName) {
        this.academicClassLevelName = academicClassLevelName;
    }

    public String getClassStreamName() {
        return classStreamName;
    }

    public void setClassStreamName(String classStreamName) {
        this.classStreamName = classStreamName;
    }
}
