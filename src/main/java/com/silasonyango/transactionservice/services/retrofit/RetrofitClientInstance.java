package com.silasonyango.transactionservice.services.retrofit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class RetrofitClientInstance {
    public static Retrofit getRetrofitInstance(String baseUrl) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request().newBuilder().build();
                return chain.proceed(request);
            }
        });

        return new retrofit2.Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl).client(httpClient.build()).build();
    }
}
