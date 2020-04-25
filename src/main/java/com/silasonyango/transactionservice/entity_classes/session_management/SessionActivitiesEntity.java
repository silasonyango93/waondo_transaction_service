package com.silasonyango.transactionservice.entity_classes.session_management;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "session_activities")
public class SessionActivitiesEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SessionActivityId")
    private int sessionActivityId;

    @Column(name = "SessionActivityDescription")
    private String sessionActivityDescription;

    public int getSessionActivityId() {
        return sessionActivityId;
    }

    public void setSessionActivityId(int sessionActivityId) {
        this.sessionActivityId = sessionActivityId;
    }

    public String getSessionActivityDescription() {
        return sessionActivityDescription;
    }

    public void setSessionActivityDescription(String sessionActivityDescription) {
        this.sessionActivityDescription = sessionActivityDescription;
    }
}
