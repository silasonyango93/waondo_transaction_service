package com.silasonyango.transactionservice.dtos.fee_management;



import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

public class FeeStatementResponseDto implements java.io.Serializable{
    private int studentId;
    private String admissionNumber;
    private String studentName;
    private String gender;
    private String classDetails;
    private String residenceDetails;
    private int termBalance;
    private int annualBalance;
    private int currentyearTotal;
    private List<InstallmentsResponseDto> installmentsResponseArray;
    private List<FeeComponentsResponseDto> feeComponentsResponseDtoList;


    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClassDetails() {
        return classDetails;
    }

    public void setClassDetails(String classDetails) {
        this.classDetails = classDetails;
    }

    public String getResidenceDetails() {
        return residenceDetails;
    }

    public void setResidenceDetails(String residenceDetails) {
        this.residenceDetails = residenceDetails;
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

    public int getCurrentyearTotal() {
        return currentyearTotal;
    }

    public void setCurrentyearTotal(int currentyearTotal) {
        this.currentyearTotal = currentyearTotal;
    }

    public List<InstallmentsResponseDto> getInstallmentsResponseArray() {
        return installmentsResponseArray;
    }

    public void setInstallmentsResponseArray(List<InstallmentsResponseDto> installmentsResponseArray) {
        this.installmentsResponseArray = installmentsResponseArray;
    }

    public List<FeeComponentsResponseDto> getFeeComponentsResponseDtoList() {
        return feeComponentsResponseDtoList;
    }

    public void setFeeComponentsResponseDtoList(List<FeeComponentsResponseDto> feeComponentsResponseDtoList) {
        this.feeComponentsResponseDtoList = feeComponentsResponseDtoList;
    }
}
