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

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

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
                    Call<Object> tourResponseCall = RestClient.call().fetchTours();
                    tourResponseCall.enqueue(new Callback<Object>() {

                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {
                            Object o = response;
                            TreeMap<String,String> tourResponseTreeMap = new TreeMap<>();
                            TreeMap<String,String> tourResponseEmbedded = new TreeMap<>();
                            TreeMap<String,String> tourResponseTours = new TreeMap<>();
                            List<TourResponse> tourResponseList = new ArrayList<>();
                            tourResponseTreeMap = (TreeMap<String,String>) response.body();


                            int i =0;
                        }

                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {

                        }
                    });


                } else {
                    observableTourList.changeDataset(tourDomainList);
                }
            }
        });
    }
}
