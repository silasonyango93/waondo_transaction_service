package com.silasonyango.transactionservice.entity_classes.fee_management;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "transaction_descriptions")
public class TransactionDescriptionsEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TransactionDescriptionId")
    private int transactionDescriptionId;

    @Column(name = "TransactionDescription")
    private String transactionDescription;

    @Column(name = "TransactionDescriptionCode")
    private int transactionDescriptionCode;

    public TransactionDescriptionsEntity() {}

    public TransactionDescriptionsEntity(String transactionDescription, int transactionDescriptionCode) {
        this.transactionDescription = transactionDescription;
        this.transactionDescriptionCode = transactionDescriptionCode;
    }

    public int getTransactionDescriptionId() {
        return transactionDescriptionId;
    }

    public void setTransactionDescriptionId(int transactionDescriptionId) {
        this.transactionDescriptionId = transactionDescriptionId;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public int getTransactionDescriptionCode() {
        return transactionDescriptionCode;
    }

    public void setTransactionDescriptionCode(int transactionDescriptionCode) {
        this.transactionDescriptionCode = transactionDescriptionCode;
    }
}
