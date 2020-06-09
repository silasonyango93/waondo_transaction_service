package com.silasonyango.transactionservice.entity_classes.student_management;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "student_residence")
public class StudentResidenceEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StudentResidenceId")
    private int studentResidenceId;

    @Column(name = "StudentResidenceDescription")
    private String studentResidenceDescription;

    @Column(name = "StudentResidenceCode")
    private int studentResidenceCode;

    public StudentResidenceEntity() {
    }

    public StudentResidenceEntity(String studentResidenceDescription, int studentResidenceCode) {
        this.studentResidenceDescription = studentResidenceDescription;
        this.studentResidenceCode = studentResidenceCode;
    }

    public int getStudentResidenceId() {
        return studentResidenceId;
    }

    public void setStudentResidenceId(int studentResidenceId) {
        this.studentResidenceId = studentResidenceId;
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
}
