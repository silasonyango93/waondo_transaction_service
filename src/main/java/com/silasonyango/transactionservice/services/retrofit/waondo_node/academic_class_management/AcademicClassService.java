package com.silasonyango.transactionservice.services.retrofit.waondo_node.academic_class_management;

import com.silasonyango.transactionservice.dtos.fee_management.fee_structure.ClassFeeStructureBreakDownModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.List;

public interface AcademicClassService {
    @FormUrlEncoded
    @POST("retrieve_class_by_level_id_and_stream_name")
    Call<AcademicClassResponseDto> retrieveClassByAcademicClassLevelIdAndStreamName(@Field("academicClassLevelId") int academicClassLevelId,
                                                                                    @Field("streamName") String streamName);
}
