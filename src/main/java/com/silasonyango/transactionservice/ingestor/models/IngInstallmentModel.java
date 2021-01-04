package com.silasonyango.transactionservice.ingestor.models;

import com.google.gson.annotations.SerializedName;

public class IngInstallmentModel {
    @SerializedName("InstallmentId")
    private int installmentId;

    @SerializedName("AdmissionNo")
    private String admissionNo;

    @SerializedName("InstallmentAmount")
    private int installmentAmount;

    @SerializedName("InstallmentDate")
    private String installmentDate;

    public int getInstallmentId() {
        return installmentId;
    }

    public void setInstallmentId(int installmentId) {
        this.installmentId = installmentId;
    }

    public String getAdmissionNo() {
        return admissionNo;
    }

    public void setAdmissionNo(String admissionNo) {
        this.admissionNo = admissionNo;
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
}
