package com.silasonyango.personallibrary.models.library_resource;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "resource_brand")

public class ResourceBrandsModel implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BrandId")
    private int brandId;

    @Column(name = "UserId")
    private String userId;

    @Column(name = "BrandName")
    private String brandName;

    @Column(name = "PublishedDate")
    private String publishedDate;

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }
}
