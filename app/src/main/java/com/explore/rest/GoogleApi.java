package com.explore.rest;

import com.explore.rest.responses.GoogleMapApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleApi {
    @GET("place/findplacefromtext/json?")
    Call<GoogleMapApi> getPlace(@Query(value = "input", encoded = true) String input,
                                @Query(value = "inputtype", encoded = true) String inputtype,
                                @Query(value = "key", encoded = true) String key);
}
