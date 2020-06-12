package com.silasonyango.transactionservice.entity_classes.fee_management;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "class_fee_structure_breakdown")
public class ClassFeeStructureBreakDownEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClassFeeStructureBreakDownId")
    private int classFeeStructureBreakDownId;

    @Column(name = "ClassFeeStructureId")
    private int classFeeStructureId;

    @Column(name = "StudentResidenceId")
    private int studentResidenceId;

    @Column(name = "TermIterationId")
    private int termIterationId;

    @Column(name = "FeeAmount")
    private int feeAmount;

    public ClassFeeStructureBreakDownEntity() {
    }

    public ClassFeeStructureBreakDownEntity(int classFeeStructureId, int studentResidenceId, int termIterationId, int feeAmount) {
        this.classFeeStructureId = classFeeStructureId;
        this.studentResidenceId = studentResidenceId;
        this.termIterationId = termIterationId;
        this.feeAmount = feeAmount;
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

    public int getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(int feeAmount) {
        this.feeAmount = feeAmount;
    }
}
