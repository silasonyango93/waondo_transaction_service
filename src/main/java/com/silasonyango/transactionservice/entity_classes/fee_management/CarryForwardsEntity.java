package com.silasonyango.transactionservice.entity_classes.fee_management;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "carry_forwards")
public class CarryForwardsEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CarryFowardId")
    private int carryFowardId;

    @Column(name = "StudentId")
    private int studentId;

    @Column(name = "CarryForwardAmount")
    private int carryForwardAmount;

    @Column(name = "DateCarriedForward")
    private String dateCarriedForward;

    @Column(name = "IsAdminCarryForward")
    private int isAdminCarryForward;

    public CarryForwardsEntity() {
    }

    public CarryForwardsEntity(int studentId, int carryForwardAmount, String dateCarriedForward, int isAdminCarryForward) {
        this.studentId = studentId;
        this.carryForwardAmount = carryForwardAmount;
        this.dateCarriedForward = dateCarriedForward;
        this.isAdminCarryForward = isAdminCarryForward;
    }

    public int getCarryFowardId() {
        return carryFowardId;
    }

    public void setCarryFowardId(int carryFowardId) {
        this.carryFowardId = carryFowardId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCarryForwardAmount() {
        return carryForwardAmount;
    }

    public void setCarryForwardAmount(int carryForwardAmount) {
        this.carryForwardAmount = carryForwardAmount;
    }

    public String getDateCarriedForward() {
        return dateCarriedForward;
    }

    public void setDateCarriedForward(String dateCarriedForward) {
        this.dateCarriedForward = dateCarriedForward;
    }

    public int getIsAdminCarryForward() {
        return isAdminCarryForward;
    }

    public void setIsAdminCarryForward(int isAdminCarryForward) {
        this.isAdminCarryForward = isAdminCarryForward;
    }
}
