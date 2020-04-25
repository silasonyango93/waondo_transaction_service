package com.silasonyango.transactionservice.entity_classes.session_management;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "user_session_activities")
public class UserSessionActivitiesEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserSessionActivityId")
    private int userSessionActivityId;

    @Column(name = "SessionLogId")
    private int sessionLogId;

    @Column(name = "SessionActivityId")
    private int sessionActivityId;

    @Column(name = "SessionActivityDate")
    private String sessionActivityDate;

    public UserSessionActivitiesEntity() {

    }

    public UserSessionActivitiesEntity(int sessionLogId, int sessionActivityId, String sessionActivityDate) {
        this.sessionLogId = sessionLogId;
        this.sessionActivityId = sessionActivityId;
        this.sessionActivityDate = sessionActivityDate;
    }

    public int getUserSessionActivityId() {
        return userSessionActivityId;
    }

    public void setUserSessionActivityId(int userSessionActivityId) {
        this.userSessionActivityId = userSessionActivityId;
    }

    public int getSessionLogId() {
        return sessionLogId;
    }

    public void setSessionLogId(int sessionLogId) {
        this.sessionLogId = sessionLogId;
    }

    public int getSessionActivityId() {
        return sessionActivityId;
    }

    public void setSessionActivityId(int sessionActivityId) {
        this.sessionActivityId = sessionActivityId;
    }

    public String getSessionActivityDate() {
        return sessionActivityDate;
    }

    public void setSessionActivityDate(String sessionActivityDate) {
        this.sessionActivityDate = sessionActivityDate;
    }
}
