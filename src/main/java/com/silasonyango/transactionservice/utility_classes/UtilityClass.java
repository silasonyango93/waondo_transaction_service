package com.silasonyango.transactionservice.utility_classes;

import com.silasonyango.transactionservice.common.config.EndPoints;
import com.silasonyango.transactionservice.dtos.fee_management.FeeStatementResponseDto;
import com.silasonyango.transactionservice.dtos.fee_management.InstallmentsResponseDto;
import com.silasonyango.transactionservice.entity_classes.fee_management.FeeStatementEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.InstallmentsEntity;
import com.silasonyango.transactionservice.entity_classes.student_management.StudentEntity;
import com.silasonyango.transactionservice.repository.fee_management.CarryForwardsRepository;
import com.silasonyango.transactionservice.repository.fee_management.FeeCorrectionsRepository;
import com.silasonyango.transactionservice.repository.fee_management.FeeStatementRepository;
import com.silasonyango.transactionservice.repository.fee_management.InstallmentRepository;
import com.silasonyango.transactionservice.repository.student_management.StudentRepository;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UtilityClass {

    public UtilityClass() {}

    public static JSONObject getTermDetailsByDate(String searchDate) {
        JSONObject dataObject = null;
        CustomOkHttp customOkHttp = new CustomOkHttp();

        RequestBody formBody = new FormBody.Builder()
                .add("searchDate", String.valueOf(searchDate))
                .build();

        try {
            String responseString = customOkHttp.okHttpPostPassingParams(EndPoints.WAONDO_NODE_BASE_URL + "/get_term_by_date",formBody);
            JSONObject object = new JSONObject(responseString);
            JSONArray jsonArray = object.getJSONArray("results");
            dataObject  = jsonArray.length() > 0 ? jsonArray.getJSONObject(0) : null;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataObject;
    }






    public static JSONObject getAStudentClassDetails(int studentId) {
        JSONObject dataObject = null;
        CustomOkHttp customOkHttp = new CustomOkHttp();

        RequestBody formBody = new FormBody.Builder()
                .add("studentId", String.valueOf(studentId))
                .build();

        try {
            String responseString = customOkHttp.okHttpPostPassingParams(EndPoints.WAONDO_NODE_BASE_URL + "/get_a_student_class_details",formBody);
            JSONObject object = new JSONObject(responseString);
            JSONArray jsonArray = object.getJSONArray("results");
            dataObject  = jsonArray.length() > 0 ? jsonArray.getJSONObject(0) : null;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataObject;
    }






    public static JSONArray getFeeStructureForParticularClassLevel(int academicClassLevelId, int studentResidenceId) {
        JSONArray dataArray = null;
        CustomOkHttp customOkHttp = new CustomOkHttp();

        RequestBody formBody = new FormBody.Builder()
                .add("academicClassLevelId", String.valueOf(academicClassLevelId))
                .add("studentResidenceId", String.valueOf(studentResidenceId))
                .build();

        try {
            String responseString = customOkHttp.okHttpPostPassingParams(EndPoints.WAONDO_NODE_BASE_URL + "/get_fee_structure_for_particular_class_level",formBody);
            JSONObject object = new JSONObject(responseString);
            dataArray = object.getJSONArray("results");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataArray;
    }






    public static int getAStudentAnnualBalanceFromTermBalance(int studentId, int termBalance, int studentResidenceId) {
        int annualBalance = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        JSONObject classDetailsObject = getAStudentClassDetails(studentId);
        JSONArray feeStructureBreakDownArray = getFeeStructureForParticularClassLevel(classDetailsObject.getInt("AcademicClassLevelId"), studentResidenceId);
        System.out.println();
        for (int i = 0;i<feeStructureBreakDownArray.length();i++) {
            if(getTermDetailsByDate(dtf.format(now)).getInt("TermIterationId") != feeStructureBreakDownArray.getJSONObject(i).getInt("TermIterationId")) {

                if(getTermDetailsByDate(dtf.format(now)).getInt("TermIterationId") < feeStructureBreakDownArray.getJSONObject(i).getInt("TermIterationId")) {
                    annualBalance = annualBalance + feeStructureBreakDownArray.getJSONObject(i).getInt("FeeAmount");
                }

            }
        }

        return annualBalance + termBalance;
    }






    public static String getNow() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        return dtf.format(now);
    }






    public static String getToday() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        return dtf.format(now);
    }






    public static String getCurrentYear() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime now = LocalDateTime.now();

        return dtf.format(now);
    }






    public static JSONArray getInstallmentsBetweenACertainPeriod(String startDate, String endDate) {
        JSONArray dataArray = null;
        CustomOkHttp customOkHttp = new CustomOkHttp();

        RequestBody formBody = new FormBody.Builder()
                .add("startDate", String.valueOf(startDate))
                .add("endDate", String.valueOf(endDate))
                .build();

        try {
            String responseString = customOkHttp.okHttpPostPassingParams(EndPoints.WAONDO_NODE_BASE_URL + "/get_installment_between_certain_period",formBody);
            JSONObject object = new JSONObject(responseString);
            dataArray = object.getJSONArray("results");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataArray;
    }








    public static JSONArray getInstallmentsForParticularStudentBetweenACertainPeriod(int studentId,String startDate, String endDate) {
        JSONArray dataArray = null;
        CustomOkHttp customOkHttp = new CustomOkHttp();

        RequestBody formBody = new FormBody.Builder()
                .add("studentId", String.valueOf(studentId))
                .add("startDate", String.valueOf(startDate))
                .add("endDate", String.valueOf(endDate))
                .build();

        try {
            String responseString = customOkHttp.okHttpPostPassingParams(EndPoints.WAONDO_NODE_BASE_URL + "/get_installment_for_particular_student_between_certain_period",formBody);
            JSONObject object = new JSONObject(responseString);
            dataArray = object.getJSONArray("results");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataArray;
    }







    public static JSONObject getAStudentResidenceDetails(int studentId) {
        JSONObject dataObject = null;
        CustomOkHttp customOkHttp = new CustomOkHttp();

        RequestBody formBody = new FormBody.Builder()
                .add("studentId", String.valueOf(studentId))
                .build();

        try {
            String responseString = customOkHttp.okHttpPostPassingParams(EndPoints.WAONDO_NODE_BASE_URL + "/get_a_student_residence_details",formBody);
            JSONObject object = new JSONObject(responseString);
            JSONArray jsonArray = object.getJSONArray("results");
            dataObject  = jsonArray.length() > 0 ? jsonArray.getJSONObject(0) : null;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataObject;
    }






    public static JSONObject getAUserByASessionLogId(int sessionLogId) {
        JSONObject dataObject = null;
        CustomOkHttp customOkHttp = new CustomOkHttp();

        RequestBody formBody = new FormBody.Builder()
                .add("sessionLogId", String.valueOf(sessionLogId))
                .build();

        try {
            String responseString = customOkHttp.okHttpPostPassingParams(EndPoints.WAONDO_NODE_BASE_URL + "/get_user_by_session_log",formBody);
            JSONObject object = new JSONObject(responseString);
            JSONArray jsonArray = object.getJSONArray("results");
            dataObject  = jsonArray.length() > 0 ? jsonArray.getJSONObject(0) : null;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataObject;
    }





    public static JSONArray getAStudentFeeComponents(int studentId) {
        JSONArray dataArray = null;
        CustomOkHttp customOkHttp = new CustomOkHttp();

        RequestBody formBody = new FormBody.Builder()
                .add("studentId", String.valueOf(studentId))
                .build();

        try {
            String responseString = customOkHttp.okHttpPostPassingParams(EndPoints.WAONDO_NODE_BASE_URL + "/get_a_student_fee_components",formBody);
            JSONObject object = new JSONObject(responseString);
            dataArray = object.getJSONArray("results");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataArray;
    }





    public static JSONArray getAllStudentsWithAMinimumTermBalance(int minimunTermBalance) {
        JSONArray dataArray = null;
        CustomOkHttp customOkHttp = new CustomOkHttp();

        RequestBody formBody = new FormBody.Builder()
                .add("minimunTermBalance", String.valueOf(minimunTermBalance))
                .build();

        try {
            String responseString = customOkHttp.okHttpPostPassingParams(EndPoints.WAONDO_NODE_BASE_URL + "/get_all_students_with_minimum_term_balance",formBody);
            JSONObject object = new JSONObject(responseString);
            dataArray = object.getJSONArray("results");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataArray;
    }






    public static JSONArray getAllStudentsInAClassWithAMinimumTermBalance(int classId, int minimunTermBalance) {
        JSONArray dataArray = null;
        CustomOkHttp customOkHttp = new CustomOkHttp();

        RequestBody formBody = new FormBody.Builder()
                .add("classId", String.valueOf(classId))
                .add("minimunTermBalance", String.valueOf(minimunTermBalance))
                .build();

        try {
            String responseString = customOkHttp.okHttpPostPassingParams(EndPoints.WAONDO_NODE_BASE_URL + "/get_all_students_in_a_class_with_minimum_term_balance",formBody);
            JSONObject object = new JSONObject(responseString);
            dataArray = object.getJSONArray("results");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataArray;
    }




    public static JSONObject getTheCurrentWeek(String todaysDate) {
        JSONObject dataObject = null;
        CustomOkHttp customOkHttp = new CustomOkHttp();

        RequestBody formBody = new FormBody.Builder()
                .add("todaysDate", String.valueOf(todaysDate))
                .build();

        try {
            String responseString = customOkHttp.okHttpPostPassingParams(EndPoints.WAONDO_NODE_BASE_URL + "/get_the_current_week",formBody);
            JSONObject object = new JSONObject(responseString);
            JSONArray jsonArray = object.getJSONArray("results");
            dataObject  = jsonArray.length() > 0 ? jsonArray.getJSONObject(0) : null;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataObject;
    }




    public static JSONObject getTermDetailsByTermId(int termId) {
        JSONObject dataObject = null;
        CustomOkHttp customOkHttp = new CustomOkHttp();

        RequestBody formBody = new FormBody.Builder()
                .add("termId", String.valueOf(termId))
                .build();

        try {
            String responseString = customOkHttp.okHttpPostPassingParams(EndPoints.WAONDO_NODE_BASE_URL + "/get_term_by_term_id",formBody);
            JSONObject object = new JSONObject(responseString);
            JSONArray jsonArray = object.getJSONArray("results");
            dataObject  = jsonArray.length() > 0 ? jsonArray.getJSONObject(0) : null;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataObject;
    }

}
