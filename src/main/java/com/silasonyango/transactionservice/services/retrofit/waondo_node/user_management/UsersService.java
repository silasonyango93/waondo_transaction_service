package com.silasonyango.transactionservice.services.retrofit.waondo_node.user_management;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UsersService {
    @POST("get_all_users")
    Call<AllUsersResponse> getAllUsers();
}
