package com.silasonyango.transactionservice.dtos.fee_management;

import com.silasonyango.transactionservice.dtos.student_management.StudentsListDto;

public class FeeBalanceListDto extends StudentsListDto {
    private int termBalance;
    private int annualBalance;
    private int total;

    public FeeBalanceListDto(int studentId, String admissionNumber, String studentName, String gender, String classDetails, String residenceDetails, int total, int termBalance, int annualBalance) {
        super(studentId,admissionNumber,studentName,gender,classDetails,residenceDetails);
        this.termBalance = termBalance;
        this.annualBalance = annualBalance;
        this.total = total;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
