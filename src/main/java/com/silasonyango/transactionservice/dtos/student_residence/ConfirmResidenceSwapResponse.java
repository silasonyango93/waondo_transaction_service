package com.silasonyango.transactionservice.dtos.student_residence;

import com.silasonyango.transactionservice.dtos.fee_management.FeeStatementResponseDto;

public class ConfirmResidenceSwapResponse {
    private String admissionNumber;
    private boolean isPeriodEligible;
    private String eligibilityMessage;
    private int currentResidenceCode;
    private int proposedResidenceCode;
    private double currentTermBalance;
    private double currentAnnualBalance;
    private double expectedTermBalance;
    private double expectedAnnualBalance;
    private double changeExtraCharge;


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

    public double getCurrentTermBalance() {
        return currentTermBalance;
    }

    public void setCurrentTermBalance(double currentTermBalance) {
        this.currentTermBalance = currentTermBalance;
    }

    public double getCurrentAnnualBalance() {
        return currentAnnualBalance;
    }

    public void setCurrentAnnualBalance(double currentAnnualBalance) {
        this.currentAnnualBalance = currentAnnualBalance;
    }

    public double getExpectedTermBalance() {
        return expectedTermBalance;
    }

    public void setExpectedTermBalance(double expectedTermBalance) {
        this.expectedTermBalance = expectedTermBalance;
    }

    public double getExpectedAnnualBalance() {
        return expectedAnnualBalance;
    }

    public void setExpectedAnnualBalance(double expectedAnnualBalance) {
        this.expectedAnnualBalance = expectedAnnualBalance;
    }

    public double getChangeExtraCharge() {
        return changeExtraCharge;
    }

    public void setChangeExtraCharge(double changeExtraCharge) {
        this.changeExtraCharge = changeExtraCharge;
    }

    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }
}
