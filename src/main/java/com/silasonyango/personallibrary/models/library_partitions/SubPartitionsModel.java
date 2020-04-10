package com.silasonyango.personallibrary.models.library_partitions;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "subpartitions")

public class SubPartitionsModel implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SubPartitionId")
    private int subPartitionId;

    @Column(name = "PartitionId")
    private String partitionId;

    @Column(name = "SubPartitionRefNo")
    private String subPartitionRefNo;

    @Column(name = "SubPartitionDescription")
    private String subPartitionDescription;

    public int getSubPartitionId() {
        return subPartitionId;
    }

    public void setSubPartitionId(int subPartitionId) {
        this.subPartitionId = subPartitionId;
    }

    public String getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(String partitionId) {
        this.partitionId = partitionId;
    }

    public String getSubPartitionRefNo() {
        return subPartitionRefNo;
    }

    public void setSubPartitionRefNo(String subPartitionRefNo) {
        this.subPartitionRefNo = subPartitionRefNo;
    }

    public String getSubPartitionDescription() {
        return subPartitionDescription;
    }

    public void setSubPartitionDescription(String subPartitionDescription) {
        this.subPartitionDescription = subPartitionDescription;
    }
}
