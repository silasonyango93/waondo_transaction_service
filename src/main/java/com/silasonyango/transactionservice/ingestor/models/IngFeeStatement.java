package com.silasonyango.transactionservice.ingestor.models;

import com.google.gson.annotations.SerializedName;

public class IngFeeStatement {
    @SerializedName("FeeId")
    private int feeId;

    @SerializedName("AdmissionNo")
    private String admissionNo;

    @SerializedName("LunchScheme")
    private int lunchScheme;

    @SerializedName("PE")
    private int pe;

    @SerializedName("EW")
    private int ew;

    @SerializedName("LT")
    private int lt;

    @SerializedName("RMI")
    private int rmi;

    @SerializedName("Administration")
    private int administration;

    @SerializedName("Activity")
    private int activity;

    @SerializedName("Total")
    private int total;

    @SerializedName("RecentPaidDate")
    private String recentPaidDate;

    @SerializedName("TermBalance")
    private int termBalance;

    @SerializedName("AnnualBalance")
    private int annualBalance;

    public int getFeeId() {
        return feeId;
    }

    public void setFeeId(int feeId) {
        this.feeId = feeId;
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

    public int getPe() {
        return pe;
    }

    public void setPe(int pe) {
        this.pe = pe;
    }

    public int getEw() {
        return ew;
    }

    public void setEw(int ew) {
        this.ew = ew;
    }

    public int getLt() {
        return lt;
    }

    public void setLt(int lt) {
        this.lt = lt;
    }

    public int getRmi() {
        return rmi;
    }

    public void setRmi(int rmi) {
        this.rmi = rmi;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getRecentPaidDate() {
        return recentPaidDate;
    }

    public void setRecentPaidDate(String recentPaidDate) {
        this.recentPaidDate = recentPaidDate;
    }

    public int getTermBalance() {
        return termBalance;
    }

    public void setTermBalance(int termBalance) {
        this.termBalance = termBalance;
    }

    public int getAnnualBalance() {
        return annualBalance;
    }

    public void setAnnualBalance(int annualBalance) {
        this.annualBalance = annualBalance;
    }
}
