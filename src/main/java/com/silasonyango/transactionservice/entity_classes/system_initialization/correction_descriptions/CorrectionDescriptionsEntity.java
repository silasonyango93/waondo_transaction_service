package com.silasonyango.transactionservice.entity_classes.system_initialization.correction_descriptions;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "correction_descriptions")
public class CorrectionDescriptionsEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CorrectionDescriptionId")
    private int correctionDescriptionId;

    @Column(name = "CorrectionDescription")
    private String correctionDescription;

    @Column(name = "CorrectionDescriptionCode")
    private int correctionDescriptionCode;

    public CorrectionDescriptionsEntity() {
    }

    public CorrectionDescriptionsEntity(String correctionDescription, int correctionDescriptionCode) {
        this.correctionDescription = correctionDescription;
        this.correctionDescriptionCode = correctionDescriptionCode;
    }

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

    public int getCorrectionDescriptionCode() {
        return correctionDescriptionCode;
    }

    public void setCorrectionDescriptionCode(int correctionDescriptionCode) {
        this.correctionDescriptionCode = correctionDescriptionCode;
    }
}
