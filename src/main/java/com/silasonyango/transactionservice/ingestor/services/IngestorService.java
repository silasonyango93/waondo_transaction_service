package com.silasonyango.transactionservice.ingestor.services;

import com.silasonyango.transactionservice.ingestor.models.IngStudentModel;
import com.silasonyango.transactionservice.services.retrofit.waondo_node.fee_management.fee_structure.ClassFeeStructureServiceModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.List;

public interface IngestorService {
    @POST("fetch_all_students")
    Call<List<IngStudentModel>> getAllStudents();
}
