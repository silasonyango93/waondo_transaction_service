package com.silasonyango.transactionservice.ingestor.services;

import com.silasonyango.transactionservice.ingestor.models.IngFeeStatement;
import com.silasonyango.transactionservice.ingestor.models.IngInstallmentModel;
import com.silasonyango.transactionservice.ingestor.models.IngStudentModel;
import com.silasonyango.transactionservice.services.retrofit.waondo_node.academic_class_management.AcademicClassResponseDto;
import com.silasonyango.transactionservice.services.retrofit.waondo_node.fee_management.fee_structure.ClassFeeStructureServiceModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.List;

public interface IngestorService {
    @POST("fetch_all_students")
    Call<List<IngStudentModel>> getAllStudents();

    @FormUrlEncoded
    @POST("fetch_a_student_fee_statement")
    Call<IngFeeStatement> fetchAStudentFeeStatement(@Field("admissionNumber") String admissionNumber);

    @FormUrlEncoded
    @POST("fetch_a_student_installments")
    Call<List<IngInstallmentModel>> fetchAStudentInstallments(@Field("admissionNumber") String admissionNumber);
}
