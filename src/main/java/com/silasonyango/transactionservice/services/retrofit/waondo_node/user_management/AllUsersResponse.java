package com.silasonyango.transactionservice.services.retrofit.waondo_node.user_management;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllUsersResponse {
    @SerializedName("results")
    private List<UserResponse> users;

    public List<UserResponse> getUsers() {
        return users;
    }

    public void setUsers(List<UserResponse> users) {
        this.users = users;
    }
}
