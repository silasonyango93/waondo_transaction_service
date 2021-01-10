package com.silasonyango.transactionservice.services.retrofit.waondo_node.fee_management.transactions;

import com.silasonyango.transactionservice.services.retrofit.waondo_node.fee_management.fee_structure.ClassFeeStructureServiceModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.List;

public interface TransactionsService {
    @FormUrlEncoded
    @POST("fetch_transactions_by_date")
    Call<List<TransactionsResponseDto>> fetchTransactionsByDate(@Field("transactionDate") String transactionDate);

    @FormUrlEncoded
    @POST("fetch_transactions_by_date_range")
    Call<List<TransactionsResponseDto>> fetchTransactionsByDateRange(@Field("transactionStartDate") String transactionStartDate,@Field("transactionEndDate") String transactionEndDate);
}
