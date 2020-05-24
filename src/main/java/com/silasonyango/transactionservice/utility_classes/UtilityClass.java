package com.silasonyango.transactionservice.utility_classes;

import com.silasonyango.transactionservice.common.config.EndPoints;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilityClass {

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
            dataObject  = jsonArray.getJSONObject(0);

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
            dataObject  = jsonArray.getJSONObject(0);

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
}
