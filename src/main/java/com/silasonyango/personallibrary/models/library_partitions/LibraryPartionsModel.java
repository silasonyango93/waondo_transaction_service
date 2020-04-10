package com.silasonyango.personallibrary.models.library_partitions;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "library_partitions")
public class LibraryPartionsModel implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PartitionId")
    private int partitionId;

    @Column(name = "UserId")
    private String userId;

    @Column(name = "PartionRefNo")
    private String partitionRefNo;

    @Column(name = "PartitionDescription")
    private String partitionDescription;


    public int getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(int partitionId) {
        this.partitionId = partitionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPartitionRefNo() {
        return partitionRefNo;
    }

    public void setPartitionRefNo(String partitionRefNo) {
        this.partitionRefNo = partitionRefNo;
    }

    public String getPartitionDescription() {
        return partitionDescription;
    }

    public void setPartitionDescription(String partitionDescription) {
        this.partitionDescription = partitionDescription;
    }

}
