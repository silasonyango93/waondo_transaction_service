package com.silasonyango.transactionservice.dtos.fee_management;

public class ManualFeeTransitionRequestDto {
    private String year;
    private String carryForwardInstallmentDate;
    private String carryForwardInstallmentYear;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCarryForwardInstallmentDate() {
        return carryForwardInstallmentDate;
    }

    public void setCarryForwardInstallmentDate(String carryForwardInstallmentDate) {
        this.carryForwardInstallmentDate = carryForwardInstallmentDate;
    }

    public String getCarryForwardInstallmentYear() {
        return carryForwardInstallmentYear;
    }

    public void setCarryForwardInstallmentYear(String carryForwardInstallmentYear) {
        this.carryForwardInstallmentYear = carryForwardInstallmentYear;
    }
}
