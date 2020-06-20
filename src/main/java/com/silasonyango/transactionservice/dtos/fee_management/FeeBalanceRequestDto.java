package com.silasonyango.transactionservice.dtos.fee_management;

public class FeeBalanceRequestDto {
    private int minimumFeeBalance;

    public FeeBalanceRequestDto(int minimumFeeBalance) {
        this.minimumFeeBalance = minimumFeeBalance;
    }

    public int getMinimumFeeBalance() {
        return minimumFeeBalance;
    }

    public void setMinimumFeeBalance(int minimumFeeBalance) {
        this.minimumFeeBalance = minimumFeeBalance;
    }
}
