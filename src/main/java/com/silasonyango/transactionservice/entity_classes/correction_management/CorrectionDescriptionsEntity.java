package com.silasonyango.transactionservice.entity_classes.correction_management;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "correction_descriptions")
public class CorrectionDescriptionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CorrectionDescriptionId")
    private int correctionDescriptionId;

    @Column(name = "CorrectionDescription")
    private String correctionDescription;

    @Column(name = "CorrectionDescriptionCode")
    private String correctionDescriptionCode;

    public int getCorrectionDescriptionId() {
        return correctionDescriptionId;
    }

    public void setCorrectionDescriptionId(int correctionDescriptionId) {
        this.correctionDescriptionId = correctionDescriptionId;
    }

    public String getCorrectionDescription() {
        return correctionDescription;
    }

    public void setCorrectionDescription(String correctionDescription) {
        this.correctionDescription = correctionDescription;
    }

    public String getCorrectionDescriptionCode() {
        return correctionDescriptionCode;
    }

    public void setCorrectionDescriptionCode(String correctionDescriptionCode) {
        this.correctionDescriptionCode = correctionDescriptionCode;
    }
}
