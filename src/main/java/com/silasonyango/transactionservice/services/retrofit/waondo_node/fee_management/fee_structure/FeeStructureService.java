package com.silasonyango.transactionservice.services.retrofit.waondo_node.fee_management.fee_structure;

import com.silasonyango.transactionservice.dtos.fee_management.fee_structure.ClassFeeStructureBreakDownModel;
import com.silasonyango.transactionservice.dtos.fee_management.fee_structure.ClassFeeStructureComponentModel;
import com.silasonyango.transactionservice.services.retrofit.waondo_node.user_management.AllUsersResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.List;

public interface FeeStructureService {

    @FormUrlEncoded
    @POST("get_class_fee_structures_of_a_fee_structure")
    Call<List<ClassFeeStructureServiceModel>> getClassFeeStructuresOfAParticularFeeStructure(@Field("feeStructureId") int feeStructureId);

    @FormUrlEncoded
    @POST("retrieve_fee_breakdown_of_particular_class_fee_structure")
    Call<List<ClassFeeStructureBreakDownModel>> getFeeBreakDownOfAParticularClassFeeStructure(@Field("classFeeStructureId") int classFeeStructureId);

    @FormUrlEncoded
    @POST("retrieve_fee_components_of_a_class_fee_structure")
    Call<List<ClassFeeStructureComponentModel>> getFeeComponentsOfAParticularClassFeeStructure(@Field("classFeeStructureId") int classFeeStructureId);
}
