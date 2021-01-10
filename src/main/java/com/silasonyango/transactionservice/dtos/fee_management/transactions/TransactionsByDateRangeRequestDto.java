package com.silasonyango.transactionservice.dtos.fee_management.transactions;

public class TransactionsByDateRangeRequestDto {
    private String transactionsStartDate;
    private String transactionsEndDate;

    public String getTransactionsStartDate() {
        return transactionsStartDate;
    }

    public void setTransactionsStartDate(String transactionsStartDate) {
        this.transactionsStartDate = transactionsStartDate;
    }

    public String getTransactionsEndDate() {
        return transactionsEndDate;
    }

    public void setTransactionsEndDate(String transactionsEndDate) {
        this.transactionsEndDate = transactionsEndDate;
    }
}
