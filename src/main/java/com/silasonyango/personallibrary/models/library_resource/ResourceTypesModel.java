package com.silasonyango.personallibrary.models.library_resource;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "resource_types")


public class ResourceTypesModel   implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ResourceTypeId")
    private int resourceTypeId;

    @Column(name = "UserId")
    private String userId;

    @Column(name = "ResourceTypeName")
    private String resourceTypeName;

    @Column(name = "ResourceTypeDescription")
    private String resourceTypeDescription;

    public int getResourceTypeId() {
        return resourceTypeId;
    }

    public void setResourceTypeId(int resourceTypeId) {
        this.resourceTypeId = resourceTypeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getResourceTypeName() {
        return resourceTypeName;
    }

    public void setResourceTypeName(String resourceTypeName) {
        this.resourceTypeName = resourceTypeName;
    }

    public String getResourceTypeDescription() {
        return resourceTypeDescription;
    }

    public void setResourceTypeDescription(String resourceTypeDescription) {
        this.resourceTypeDescription = resourceTypeDescription;
    }

}
