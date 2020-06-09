package com.silasonyango.transactionservice.entity_classes.system_initialization.system_configuration;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "system_configuration")
public class SystemConfigurationEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SystemConfigurationId")
    private int systemConfigurationId;

    @Column(name = "SystemConfigurationDescription")
    private String systemConfigurationDescription;

    @Column(name = "SystemConfigurationCode")
    private int systemConfigurationCode;

    public SystemConfigurationEntity() {
    }

    public SystemConfigurationEntity(String systemConfigurationDescription, int systemConfigurationCode) {
        this.systemConfigurationDescription = systemConfigurationDescription;
        this.systemConfigurationCode = systemConfigurationCode;
    }

    public int getSystemConfigurationId() {
        return systemConfigurationId;
    }

    public void setSystemConfigurationId(int systemConfigurationId) {
        this.systemConfigurationId = systemConfigurationId;
    }

    public String getSystemConfigurationDescription() {
        return systemConfigurationDescription;
    }

    public void setSystemConfigurationDescription(String systemConfigurationDescription) {
        this.systemConfigurationDescription = systemConfigurationDescription;
    }

    public int getSystemConfigurationCode() {
        return systemConfigurationCode;
    }

    public void setSystemConfigurationCode(int systemConfigurationCode) {
        this.systemConfigurationCode = systemConfigurationCode;
    }
}
