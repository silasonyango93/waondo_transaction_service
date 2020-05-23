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
    private int transactionDescription;

    public TransactionDescriptionsEntity() {}

    public TransactionDescriptionsEntity(int transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public int getTransactionDescriptionId() {
        return transactionDescriptionId;
    }

    public void setTransactionDescriptionId(int transactionDescriptionId) {
        this.transactionDescriptionId = transactionDescriptionId;
    }

    public int getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(int transactionDescription) {
        this.transactionDescription = transactionDescription;
    }
}
