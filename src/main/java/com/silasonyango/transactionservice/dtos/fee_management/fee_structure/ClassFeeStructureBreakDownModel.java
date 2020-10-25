package com.silasonyango.transactionservice.dtos.fee_management.fee_structure;

public class ClassFeeStructureBreakDownModel {
    private int classFeeStructureBreakDownId;
    private int classFeeStructureId;
    private int studentResidenceId;
    private int termIterationId;
    private double feeAmount;
    private String studentResidenceDescription;
    private int studentResidenceCode;
    private String termIterationDescription;
    private int termIterationCode;

    public ClassFeeStructureBreakDownModel() {
    }

    public ClassFeeStructureBreakDownModel(int classFeeStructureBreakDownId, int classFeeStructureId, int studentResidenceId, int termIterationId, double feeAmount, String studentResidenceDescription, int studentResidenceCode, String termIterationDescription, int termIterationCode) {
        this.classFeeStructureBreakDownId = classFeeStructureBreakDownId;
        this.classFeeStructureId = classFeeStructureId;
        this.studentResidenceId = studentResidenceId;
        this.termIterationId = termIterationId;
        this.feeAmount = feeAmount;
        this.studentResidenceDescription = studentResidenceDescription;
        this.studentResidenceCode = studentResidenceCode;
        this.termIterationDescription = termIterationDescription;
        this.termIterationCode = termIterationCode;
    }

    public int getClassFeeStructureBreakDownId() {
        return classFeeStructureBreakDownId;
    }

    public void setClassFeeStructureBreakDownId(int classFeeStructureBreakDownId) {
        this.classFeeStructureBreakDownId = classFeeStructureBreakDownId;
    }

    public int getClassFeeStructureId() {
        return classFeeStructureId;
    }

    public void setClassFeeStructureId(int classFeeStructureId) {
        this.classFeeStructureId = classFeeStructureId;
    }

    public int getStudentResidenceId() {
        return studentResidenceId;
    }

    public void setStudentResidenceId(int studentResidenceId) {
        this.studentResidenceId = studentResidenceId;
    }

    public int getTermIterationId() {
        return termIterationId;
    }

    public void setTermIterationId(int termIterationId) {
        this.termIterationId = termIterationId;
    }

    public double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(double feeAmount) {
        this.feeAmount = feeAmount;
    }

    public String getStudentResidenceDescription() {
        return studentResidenceDescription;
    }

    public void setStudentResidenceDescription(String studentResidenceDescription) {
        this.studentResidenceDescription = studentResidenceDescription;
    }

    public int getStudentResidenceCode() {
        return studentResidenceCode;
    }

    public void setStudentResidenceCode(int studentResidenceCode) {
        this.studentResidenceCode = studentResidenceCode;
    }

    public String getTermIterationDescription() {
        return termIterationDescription;
    }

    public void setTermIterationDescription(String termIterationDescription) {
        this.termIterationDescription = termIterationDescription;
    }

    public int getTermIterationCode() {
        return termIterationCode;
    }

    public void setTermIterationCode(int termIterationCode) {
        this.termIterationCode = termIterationCode;
    }
}
