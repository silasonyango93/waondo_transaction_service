package com.silasonyango.transactionservice.dtos.student_residence;

public class ConfirmResidenceSwapResponse {
    private boolean isPeriodEligible;
    private String eligibilityMessage;
    private int currentResidenceCode;
    private int proposedResidenceCode;

    public boolean isPeriodEligible() {
        return isPeriodEligible;
    }

    public void setPeriodEligible(boolean periodEligible) {
        isPeriodEligible = periodEligible;
    }

    public String getEligibilityMessage() {
        return eligibilityMessage;
    }

    public void setEligibilityMessage(String eligibilityMessage) {
        this.eligibilityMessage = eligibilityMessage;
    }

    public int getCurrentResidenceCode() {
        return currentResidenceCode;
    }

    public void setCurrentResidenceCode(int currentResidenceCode) {
        this.currentResidenceCode = currentResidenceCode;
    }

    public int getProposedResidenceCode() {
        return proposedResidenceCode;
    }

    public void setProposedResidenceCode(int proposedResidenceCode) {
        this.proposedResidenceCode = proposedResidenceCode;
    }
}
