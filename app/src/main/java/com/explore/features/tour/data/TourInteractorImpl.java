package com.explore.features.tour.data;

import android.content.Context;
import android.os.AsyncTask;

import com.explore.base.ExploreDatabase;
import com.explore.base.PresenterObserver;
import com.explore.features.tour.domain.TourDomain;
import com.explore.features.tour.domain.TourInteractor;
import com.explore.features.tour.observers.ObservableTourList;
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
    public void getTourList(PresenterObserver presenterObserver, Context context, final String tourPackageId, final boolean userRefresh) {
        final TourDao tourDao = ExploreDatabase.getDatabase(context).tourDao();
        observableTourList.setTourDomainList(tourDomainList);
        observableTourList.addObserver(presenterObserver);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                tourDomainList = tourDao.getTours(tourPackageId);


                if (tourDomainList.isEmpty() || userRefresh == true) {
                    tourDomainList.clear();


                    Call<List<TourResponse>> tourResponseCall = RestClient.call().fetchTours(tourPackageId);
                    tourResponseCall.enqueue(new Callback<List<TourResponse>>() {

                        private void updateToursData() {
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    tourDao.updateToursDb(tourDomainList);
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
                            updateToursData();
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
