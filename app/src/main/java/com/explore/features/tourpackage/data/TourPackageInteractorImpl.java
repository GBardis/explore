package com.explore.features.tourpackage.data;

import android.content.Context;
import android.databinding.ObservableList;
import android.os.AsyncTask;

import com.explore.base.ExploreDatabase;
import com.explore.features.tourpackage.PresenterObserver;
import com.explore.features.tourpackage.domain.TourPackageDomain;
import com.explore.features.tourpackage.domain.TourPackageInteractor;
import com.explore.rest.RestClient;
import com.explore.rest.responses.TourPackageResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class TourPackageInteractorImpl implements TourPackageInteractor {
    List<TourPackageDomain> tourPackageDomainList = new ArrayList<>();
    ObservableList<TourPackageDomain> tourPackageDomainObservableArrayList;
    ObservableTourPackageList observableTourPackageList = new ObservableTourPackageList();

    @Override
    public void getTourPackages(PresenterObserver presenterObserver, final Context context) {
        final TourPackageDao tourPackageDao = ExploreDatabase.getDatabase(context).tourPackageDao();
        observableTourPackageList.setTourPackageDomainList(tourPackageDomainList);
        observableTourPackageList.addObserver(presenterObserver);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                tourPackageDomainList = tourPackageDao.getTourPackages();


                if (tourPackageDomainList.isEmpty()) {

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


                        @Override
                        public void onResponse(Call<List<TourPackageResponse>> call, Response<List<TourPackageResponse>> response) {
                            List<TourPackageResponse> tourPackageResponseList = response.body();
                            for (TourPackageResponse tourPackageResponse : tourPackageResponseList) {
                                tourPackageDomainList.add(new TourPackageDomain(
                                        tourPackageResponse.getId(),
                                        tourPackageResponse.getName(),
                                        tourPackageResponse.getAverageReviewScore(),
                                        tourPackageResponse.getRegion()
                                ));

                            }

                            insertTourPackageListToDb(tourPackageDomainList);
                            Timber.tag("INTERACTOR_TOUR_PACKAGE").d("Serving from API!");
                            observableTourPackageList.changeDataset(tourPackageDomainList);
                        }

                        @Override
                        public void onFailure(Call<List<TourPackageResponse>> call, Throwable t) {

                        }
                    });
                } else {
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            Timber.tag("INTERACTOR_TOUR_PACKAGE").d("Serving from Database!");
                            observableTourPackageList.changeDataset(tourPackageDomainList);
                        }
                    });

                }
            }
        });

    }
}
