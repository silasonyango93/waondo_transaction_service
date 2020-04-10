package com.silasonyango.personallibrary.models.library_resource;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "library_fields")

public class LibraryFieldsModel implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fieldId")
    private int fieldId;

    @Column(name = "UserId")
    private String userId;

    @Column(name = "FieldName")
    private String fieldName;

    @Column(name = "FieldDescription")
    private String fieldDescription;

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldDescription() {
        return fieldDescription;
    }

    public void setFieldDescription(String fieldDescription) {
        this.fieldDescription = fieldDescription;
    }
}
