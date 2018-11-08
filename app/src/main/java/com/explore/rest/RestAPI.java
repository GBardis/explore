package com.explore.rest;

import com.explore.features.reviewnew.domain.ReviewDomain;
import com.explore.features.user.domain.UserDomain;
import com.explore.rest.responses.ReviewResponse;
import com.explore.rest.responses.TourPackageResponse;
import com.explore.rest.responses.TourResponse;
import com.explore.rest.responses.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestAPI {

    @POST("login")
    Call<UserResponse> login(@Body UserDomain userDomain);

    @GET("tourPackages")
    Call<List<TourPackageResponse>> fetchTourPackages();

    @GET("tourPackages/{id}/tours")
    Call<List<TourResponse>> fetchTours(@Path("id") String tourPackageId);

    @GET("tourPackages/{id}/reviews")
    Call<List<ReviewResponse>> fetchReviews(@Path("id") String tourPackageId);

    @POST("tourPackages/{id}/reviews")
    Call<Void> postReview(@Path("id") String tourPackageId, @Body ReviewDomain reviewDomain);
}
