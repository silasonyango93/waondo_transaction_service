package com.silasonyango.transactionservice.utility_classes;

import com.silasonyango.transactionservice.common.config.EndPoints;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;

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
}
