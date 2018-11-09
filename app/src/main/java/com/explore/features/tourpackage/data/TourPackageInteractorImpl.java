package com.explore.features.tourpackage.data;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.explore.R;
import com.explore.base.ExploreDatabase;
import com.explore.base.PresenterObserver;
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
import timber.log.Timber;

public class TourPackageInteractorImpl implements TourPackageInteractor {
    private List<TourPackageDomain> tourPackageDomainList = new ArrayList<>();
    private ObservableTourPackageList observableTourPackageList = new ObservableTourPackageList();
    private String placeID;

    @Override
    public void getTourPackages(PresenterObserver presenterObserver, final Context context, final boolean userRefresh) {
        final TourPackageDao tourPackageDao = ExploreDatabase.getDatabase(context).tourPackageDao();
        observableTourPackageList.setTourPackageDomainList(tourPackageDomainList);
        observableTourPackageList.addObserver(presenterObserver);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                tourPackageDomainList = tourPackageDao.getTourPackages();

                if (tourPackageDomainList.isEmpty() || userRefresh == true) {
                    tourPackageDomainList.clear();


                    Call<List<TourPackageResponse>> tourPackageResponseCall = RestClient.call().fetchTourPackages();
                    tourPackageResponseCall.enqueue(new Callback<List<TourPackageResponse>>() {

                        private void insertTourPackageListToDb(final List<TourPackageDomain> responseList) {
                            Timber.tag("INTERACTOR_TOUR_PACKAGE").d("Inserting data into DB");
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    tourPackageDao.insertTourPackages(responseList);
                                }
                            });
                        }

                        private void updateDb(final List<TourPackageDomain> responseList) {
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    tourPackageDao.updateTourPackagesDb(tourPackageDomainList);
                                }
                            });
                        }

                        @Override
                        public void onResponse(@NonNull Call<List<TourPackageResponse>> call, @NonNull final Response<List<TourPackageResponse>> response) {
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    List<TourPackageResponse> tourPackageResponseList = response.body();

                                    for (TourPackageResponse tourPackageResponse : tourPackageResponseList) {

                                        placeID = getTourPackagePlaceId(tourPackageResponse.getRegion(), context);
                                        tourPackageDomainList.add(new TourPackageDomain(
                                                tourPackageResponse.getId(),
                                                tourPackageResponse.getName(),
                                                tourPackageResponse.getAverageReviewScore(),
                                                tourPackageResponse.getRegion(),
                                                placeID
                                        ));
                                    }

                                    if (userRefresh == true) {
                                        updateDb(tourPackageDomainList);
                                    } else {
                                        insertTourPackageListToDb(tourPackageDomainList);
                                    }


                                    Timber.tag("INTERACTOR_TOUR_PACKAGE").d("Serving from API!");
                                    observableTourPackageList.changeDataset(tourPackageDomainList);
                                }
                            });
                        }

                        @Override
                        public void onFailure(@NonNull Call<List<TourPackageResponse>> call, @NonNull Throwable t) {

                        }
                    });
                } else {
                    Timber.tag("INTERACTOR_TOUR_PACKAGE").d("Serving from Database!");
                    observableTourPackageList.changeDataset(tourPackageDomainList);
                }
            }
        });
    }

    String getTourPackagePlaceId(String placeName, Context context) {
        Response<GoogleMapApi> result;
        List<GooglePlaceID> candidates;
        String placeId = "";
        if (placeName.equals("Aeagean")) {
            placeName = "Aegean Islands";
        }
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
}
