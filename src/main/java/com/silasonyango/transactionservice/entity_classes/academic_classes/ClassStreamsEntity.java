package com.silasonyango.transactionservice.entity_classes.academic_classes;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "class_streams")
public class ClassStreamsEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClassStreamId")
    private int classStreamId;

    @Column(name = "ClassStreamName")
    private String classStreamName;

    @Column(name = "IsAdminClassStream")
    private int isAdminClassStream;

    public ClassStreamsEntity() {
    }

    public ClassStreamsEntity(String classStreamName, int isAdminClassStream) {
        this.classStreamName = classStreamName;
        this.isAdminClassStream = isAdminClassStream;
    }

    public int getClassStreamId() {
        return classStreamId;
    }

    public void setClassStreamId(int classStreamId) {
        this.classStreamId = classStreamId;
    }

    public String getClassStreamName() {
        return classStreamName;
    }

    public void setClassStreamName(String classStreamName) {
        this.classStreamName = classStreamName;
    }

    public int getIsAdminClassStream() {
        return isAdminClassStream;
    }

    public void setIsAdminClassStream(int isAdminClassStream) {
        this.isAdminClassStream = isAdminClassStream;
    }
}
