package com.silasonyango.transactionservice.entity_classes.session_management;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "session_logs")
public class SessionLogsEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SessionLogId")
    private int sessionLogId;

    @Column(name = "UserId")
    private int userId;

    @Column(name = "SessionStartDate")
    private String sessionStartDate;

    @Column(name = "SessionEndDate")
    private String sessionEndDate;

    @Column(name = "IsAdminSessionLog")
    private int isAdminSessionLog;

    public SessionLogsEntity() {

    }

    public SessionLogsEntity(int userId, String sessionStartDate, String sessionEndDate, int isAdminSessionLog) {
        this.userId = userId;
        this.sessionStartDate = sessionStartDate;
        this.sessionEndDate = sessionEndDate;
        this.isAdminSessionLog = isAdminSessionLog;
    }

    public SessionLogsEntity(String sessionEndDate) {
        this.sessionEndDate = sessionEndDate;
    }

    public int getSessionLogId() {
        return sessionLogId;
    }

    public void setSessionLogId(int sessionLogId) {
        this.sessionLogId = sessionLogId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSessionStartDate() {
        return sessionStartDate;
    }

    public void setSessionStartDate(String sessionStartDate) {
        this.sessionStartDate = sessionStartDate;
    }

    public String getSessionEndDate() {
        return sessionEndDate;
    }

    public void setSessionEndDate(String sessionEndDate) {
        this.sessionEndDate = sessionEndDate;
    }

    public int getIsAdminSessionLog() {
        return isAdminSessionLog;
    }

    public void setIsAdminSessionLog(int isAdminSessionLog) {
        this.isAdminSessionLog = isAdminSessionLog;
    }
}
