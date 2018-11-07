package com.explore.rest;

import com.explore.features.user.domain.UserDomain;
import com.explore.rest.responses.TourPackageResponse;
import com.explore.rest.responses.UserResponse;
import com.explore.rest.responses.tourResponse.TourRetrofitResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestAPI {

    @POST("login")
    Call<UserResponse> login(@Body UserDomain userDomain);

    @GET("tourPackages")
    Call<List<TourPackageResponse>> fetchTourPackages();

    @GET("tours")
    Call<TourRetrofitResponse> fetchTours();
}
