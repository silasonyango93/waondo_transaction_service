package com.silasonyango.transactionservice.utility_classes;

import com.silasonyango.transactionservice.common.config.EndPoints;
import okhttp3.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
public class CustomOkHttp {
    private final OkHttpClient httpClient = new OkHttpClient();

    public CustomOkHttp() {

    }

    public void okHttpGet() throws Exception {

        Request request = new Request.Builder()
                .url("https://www.google.com/search?q=mkyong")
                .addHeader("custom-key", "mkyong")  // add request headers
                .addHeader("User-Agent", "OkHttp Bot")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            // Get response body
//            JSONObject object = new JSONObject(response.body().string());
//            JSONArray jsonArray = object.getJSONArray("results");
//
//            JSONObject obj = null;
//            obj = jsonArray.getJSONObject(0);
//
//
//            System.out.println(obj.getString("Name"));



        }

    }

    public void okHttpPostPassingParams() throws Exception {

        // form parameters
        RequestBody formBody = new FormBody.Builder()
                .add("username", "abc")
                .add("password", "123")
                .add("custom", "secret")
                .build();

        Request request = new Request.Builder()
                .url("https://httpbin.org/post")
                .addHeader("User-Agent", "OkHttp Bot")
                .post(formBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            System.out.println(response.body().string());
        }

    }


    public String okHttpPostWithoutParams(String apiUrl) throws Exception {

        String responseString;
        RequestBody formBody = new FormBody.Builder().build();

        Request request = new Request.Builder()
                .url(apiUrl)
                //.addHeader("User-Agent", "OkHttp Bot")
                .post(formBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);


            responseString = response.body().string();
        }

        return responseString;

    }

}