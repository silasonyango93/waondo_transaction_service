package com.silasonyango.transactionservice.services.retrofit.waondo_node.user_management;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("UserId")
    private int userId;

    @SerializedName("Name")
    private String name;

    @SerializedName("Email")
    private String email;

    @SerializedName("GenderId")
    private int genderId;

    @SerializedName("EncryptedPassword")
    private String encryptedPassword;

    @SerializedName("RegisteredDate")
    private String registeredDate;

    @SerializedName("IsAdminUser")
    private int isAdminUser;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public int getIsAdminUser() {
        return isAdminUser;
    }

    public void setIsAdminUser(int isAdminUser) {
        this.isAdminUser = isAdminUser;
    }
}
