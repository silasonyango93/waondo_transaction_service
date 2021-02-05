package com.silasonyango.transactionservice.dtos.fee_management;

public class InstallmentDeletionRequestDto {
    private int installmentId;
    private int sessionLogId;

    public int getInstallmentId() {
        return installmentId;
    }

    public void setInstallmentId(int installmentId) {
        this.installmentId = installmentId;
    }

    public int getSessionLogId() {
        return sessionLogId;
    }

    public void setSessionLogId(int sessionLogId) {
        this.sessionLogId = sessionLogId;
    }
}
