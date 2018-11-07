package com.explore.features.tour.data;

import android.content.Context;
import android.databinding.ObservableList;
import android.os.AsyncTask;

import com.explore.base.ExploreDatabase;
import com.explore.features.tour.domain.TourDomain;
import com.explore.features.tour.domain.TourInteractor;
import com.explore.features.tourpackage.PresenterObserver;
import com.explore.rest.RestClient;
import com.explore.rest.responses.TourResponse;
import com.explore.rest.responses.tourResponse.TourRetrofitResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TourInteractorImpl implements TourInteractor {

    List<TourDomain> tourDomainList = new ArrayList<>();
    ObservableList<TourDomain> tourDomainObservableArrayList;
    ObservableTourList observableTourList = new ObservableTourList();


    @Override
    public void getTourList(PresenterObserver presenterObserver, Context context, final String tourPackageId) {
        final TourDao tourDao = ExploreDatabase.getDatabase(context).tourDao();
        observableTourList.setTourDomainList(tourDomainList);
        observableTourList.addObserver(presenterObserver);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                tourDomainList = tourDao.getTours();


                if (tourDomainList.isEmpty()) {
                    Call<TourRetrofitResponse> tourResponseCall = RestClient.call().fetchTours();
                    tourResponseCall.enqueue(new Callback<TourRetrofitResponse>() {

                        @Override
                        public void onResponse(Call<TourRetrofitResponse> call, Response<TourRetrofitResponse> response) {
                            TourRetrofitResponse retrofitResponse = response.body();
                            List<TourResponse> tourResponseList = retrofitResponse.embedded.tours;

                            for (TourResponse tourResponse : tourResponseList) {
                                tourDomainList.add(new TourDomain(
                                        tourResponse.getId(),
                                        tourResponse.getTitle(),
                                        tourResponse.getDescription(),
                                        tourResponse.getPrice(),
                                        tourResponse.getDuration(),
                                        tourResponse.getBullets(),
                                        tourResponse.getKeywords()
                                ));


//                                String title, String description, int price, String duration, String bullets, String keywords
                            }
                            observableTourList.changeDataset(tourDomainList);
                        }



                        @Override
                        public void onFailure(Call<TourRetrofitResponse> call, Throwable t) {

                        }
                    });


                } else {
                    observableTourList.changeDataset(tourDomainList);
                }
            }
        });
    }
}
