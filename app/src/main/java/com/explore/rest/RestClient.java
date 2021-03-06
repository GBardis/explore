package com.explore.rest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static RestAPI API;

    static {
        setupRestClient();
    }

    public static RestAPI call() {
        return API;
    }

    private static void setupRestClient() {

        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();


        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://explore-greece.herokuapp.com/")
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API = retrofit.create(RestAPI.class);

    }
}
