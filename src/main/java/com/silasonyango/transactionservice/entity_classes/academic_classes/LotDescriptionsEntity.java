package com.silasonyango.transactionservice.entity_classes.academic_classes;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "lot_descriptions")
public class LotDescriptionsEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LotDescriptionId")
    private int lotDescriptionId;

    @Column(name = "LotDescription")
    private String lotDescription;

    @Column(name = "IsAdminLotDescription")
    private int isAdminLotDescription;

    public LotDescriptionsEntity() {
    }

    public LotDescriptionsEntity(String lotDescription, int isAdminLotDescription) {
        this.lotDescription = lotDescription;
        this.isAdminLotDescription = isAdminLotDescription;
    }

    public int getLotDescriptionId() {
        return lotDescriptionId;
    }

    public void setLotDescriptionId(int lotDescriptionId) {
        this.lotDescriptionId = lotDescriptionId;
    }

    public String getLotDescription() {
        return lotDescription;
    }

    public void setLotDescription(String lotDescription) {
        this.lotDescription = lotDescription;
    }

    public int getIsAdminLotDescription() {
        return isAdminLotDescription;
    }

    public void setIsAdminLotDescription(int isAdminLotDescription) {
        this.isAdminLotDescription = isAdminLotDescription;
    }
}
