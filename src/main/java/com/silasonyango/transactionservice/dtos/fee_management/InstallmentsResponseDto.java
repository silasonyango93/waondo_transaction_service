package com.silasonyango.transactionservice.dtos.fee_management;

import com.silasonyango.transactionservice.entity_classes.fee_management.InstallmentsEntity;

public class InstallmentsResponseDto extends InstallmentsEntity {
    private String bursarName;
    private String termDetails;

    public InstallmentsResponseDto(int studentId,int installmentAmount,String installmentDate,int isCarryForward,int sessionLogId,int userSessionActivityId,String installmentYear,String bursarName,String termDetails) {
        super(studentId,installmentAmount,installmentDate,isCarryForward,sessionLogId,userSessionActivityId,installmentYear);
        this.bursarName = bursarName;
        this.termDetails = termDetails;
    }

    public String getBursarName() {
        return bursarName;
    }

    public void setBursarName(String bursarName) {
        this.bursarName = bursarName;
    }

    public String getTermDetails() {
        return termDetails;
    }

    public void setTermDetails(String termDetails) {
        this.termDetails = termDetails;
    }
}
