package com.explore.features.tour.data;

import android.content.Context;
import android.os.AsyncTask;

import com.explore.base.ExploreDatabase;
import com.explore.features.tour.domain.TourDomain;
import com.explore.features.tour.domain.TourInteractor;
import com.explore.features.tourpackage.PresenterObserver;
import com.explore.rest.RestClient;
import com.explore.rest.responses.TourResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class TourInteractorImpl implements TourInteractor {

    List<TourDomain> tourDomainList = new ArrayList<>();
    ObservableTourList observableTourList = new ObservableTourList();


    @Override
    public void getTourList(PresenterObserver presenterObserver, Context context, final String tourPackageId) {
        final TourDao tourDao = ExploreDatabase.getDatabase(context).tourDao();
        observableTourList.setTourDomainList(tourDomainList);
        observableTourList.addObserver(presenterObserver);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                tourDomainList = tourDao.getTours(tourPackageId);


                if (tourDomainList.isEmpty()) {
                    Call<List<TourResponse>> tourResponseCall = RestClient.call().fetchTours(tourPackageId);
                    tourResponseCall.enqueue(new Callback<List<TourResponse>>() {

                        private void insertTourListToDb(final List<TourDomain> responseList) {
                            Timber.tag("INTERACTOR_TOUR").d("Inserting data into DB");
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    tourDao.insertTours(responseList);
                                }
                            });
                        }


                        @Override
                        public void onResponse(Call<List<TourResponse>> call, Response<List<TourResponse>> response) {
                            List<TourResponse> tourResponseList = response.body();
                            for (TourResponse tourResponse : tourResponseList) {
                                tourDomainList.add(new TourDomain(
                                        tourResponse.getId(),
                                        tourResponse.getTitle(),
                                        tourResponse.getDescription(),
                                        tourResponse.getPrice(),
                                        tourResponse.getDuration(),
                                        tourResponse.getBullets(),
                                        tourResponse.getKeywords(),
                                        tourPackageId
                                ));
                            }
                            insertTourListToDb(tourDomainList);
                            Timber.tag("INTERACTOR_TOUR").d("Serving from API!");
                            observableTourList.changeDataset(tourDomainList);
                        }

                        @Override
                        public void onFailure(Call<List<TourResponse>> call, Throwable t) {

                        }
                    });


                } else {
                    Timber.tag("INTERACTOR_TOUR").d("Serving from Database!");
                    observableTourList.changeDataset(tourDomainList);

                }
            }
        });
    }
}
