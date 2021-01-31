package com.silasonyango.transactionservice.services.retrofit.waondo_node.students;

import com.silasonyango.transactionservice.entity_classes.student_management.StudentEntity;
import com.silasonyango.transactionservice.services.retrofit.waondo_node.user_management.AllUsersResponse;
import retrofit2.Call;
import retrofit2.http.POST;

import java.util.List;

public interface StudentsService {
    @POST("get_all_students_not_completed_school")
    Call<List<StudentsListResponseDto>> getAllStudentsNotCompletedSchool();
}
