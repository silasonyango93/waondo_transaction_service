package com.silasonyango.transactionservice.entity_classes.fee_management;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "student_fee_components")
public class StudentFeeComponentEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StudentFeeComponentId")
    private int studentFeeComponentId;

    @Column(name = "StudentId")
    private int studentId;

    @Column(name = "ClassFeeStructureComponentId")
    private int classFeeStructureComponentId;

    @Column(name = "ComponentFeeAmount")
    private double componentFeeAmount;

    public StudentFeeComponentEntity() {}

    public StudentFeeComponentEntity(int studentId, int classFeeStructureComponentId, int componentFeeAmount) {
        this.studentId = studentId;
        this.classFeeStructureComponentId = classFeeStructureComponentId;
        this.componentFeeAmount = componentFeeAmount;
    }

    public StudentFeeComponentEntity(int studentFeeComponentId,int studentId, int classFeeStructureComponentId, double componentFeeAmount) {
        this.studentId = studentId;
        this.classFeeStructureComponentId = classFeeStructureComponentId;
        this.componentFeeAmount = componentFeeAmount;
        this.studentFeeComponentId = studentFeeComponentId;
    }

    public int getStudentFeeComponentId() {
        return studentFeeComponentId;
    }

    public void setStudentFeeComponentId(int studentFeeComponentId) {
        this.studentFeeComponentId = studentFeeComponentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getClassFeeStructureComponentId() {
        return classFeeStructureComponentId;
    }

    public void setClassFeeStructureComponentId(int classFeeStructureComponentId) {
        this.classFeeStructureComponentId = classFeeStructureComponentId;
    }

    public double getComponentFeeAmount() {
        return componentFeeAmount;
    }

    public void setComponentFeeAmount(double componentFeeAmount) {
        this.componentFeeAmount = componentFeeAmount;
    }
}
