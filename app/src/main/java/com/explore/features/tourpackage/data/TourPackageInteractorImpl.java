package com.explore.features.tourpackage.data;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.explore.R;
import com.explore.base.ExploreDatabase;
import com.explore.features.tourpackage.domain.TourPackageDomain;
import com.explore.features.tourpackage.domain.TourPackageInteractor;
import com.explore.rest.GoogleClient;
import com.explore.rest.RestClient;
import com.explore.rest.responses.GoogleMapApi;
import com.explore.rest.responses.GooglePlaceID;
import com.explore.rest.responses.TourPackageResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TourPackageInteractorImpl implements TourPackageInteractor {

    @Override
    public void getTourPackages(final OnTourPackageListFinishListener onTourPackageListFinishListener, final Context context) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final TourPackageDao tourPackageDao = ExploreDatabase.getDatabase(context).tourPackageDao();
                List<TourPackageDomain> tourPackageDomainList = tourPackageDao.getTourPackages();

                if (tourPackageDomainList.isEmpty()) {
                    Call<List<TourPackageResponse>> tourPackageResponseCall = RestClient.call().fetchTourPackages();
                    tourPackageResponseCall.enqueue(new Callback<List<TourPackageResponse>>() {
                        @Override
                        public void onResponse(@NonNull Call<List<TourPackageResponse>> call, @NonNull final Response<List<TourPackageResponse>> response) {
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    if (response.body() != null) {

                                        List<TourPackageDomain> tourPackageDomainList = new ArrayList<>(response.body().size());

                                        List<TourPackageResponse> tourPackageResponseList = response.body();
                                        for (TourPackageResponse tourPackageResponse : tourPackageResponseList) {

                                            getTourPackagePlaceId(tourPackageResponse.getName(), context);

                                            tourPackageDomainList.add(new TourPackageDomain(
                                                    tourPackageResponse.getId(),
                                                    tourPackageResponse.getName(),
                                                    tourPackageResponse.getAverageReviewScore(),
                                                    tourPackageResponse.getRegion()
                                            ));

                                        }
                                        tourPackageDao.insertTourPackages(tourPackageDomainList);
                                        onTourPackageListFinishListener.onSuccess(tourPackageDomainList);
                                    } else {
                                        onTourPackageListFinishListener.onFailure();
                                    }
                                }
                            });
                        }

                        @Override
                        public void onFailure(@NonNull Call<List<TourPackageResponse>> call, @NonNull Throwable t) {
                            System.out.print("sdsds");
                        }
                    });
                } else {
                    onTourPackageListFinishListener.onSuccess(tourPackageDomainList);
                }
            }
        });

    }

    String getTourPackagePlaceId(String placeName, Context context) {
        Response<GoogleMapApi> result;
        List<GooglePlaceID> candidates;
        String placeId = "";
        Call<GoogleMapApi> googleMapApiCall = GoogleClient.call().getPlace(placeName, "textquery", context.getString(R.string.Google_api_key));
        try {
            result = googleMapApiCall.execute();
            candidates = Objects.requireNonNull(result.body()).candidates;
            placeId = candidates.get(0).placeId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return placeId;
    }

    @Override
    public void getTourPackage(OnTourPackageFinishListener onTourPackageFinishListener, String tourPackageId) {

    }
}
