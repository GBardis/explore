package com.explore.features.tourpackage.data;

import android.content.Context;
import android.databinding.ObservableList;
import android.os.AsyncTask;

import com.explore.base.ExploreDatabase;
import com.explore.features.tourpackage.PresenterObserver;
import com.explore.features.tourpackage.domain.TourPackageDomain;
import com.explore.features.tourpackage.domain.TourPackageInteractor;
import com.explore.features.tourpackage.presentation.TourPackagePresenterImpl;
import com.explore.rest.RestClient;
import com.explore.rest.responses.TourPackageResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TourPackageInteractorImpl implements TourPackageInteractor {
    List<TourPackageDomain> tourPackageDomainList = new ArrayList<>();
    ObservableList<TourPackageDomain> tourPackageDomainObservableArrayList;
    ObservableTourPackageList observableTourPackageList = new ObservableTourPackageList();

    @Override
    public void getTourPackages(PresenterObserver presenterObserver, final OnTourPackageListFinishListener onTourPackageListFinishListener, final Context context) {
        final TourPackageDao tourPackageDao = ExploreDatabase.getDatabase(context).tourPackageDao();
        observableTourPackageList.setTourPackageDomainList(tourPackageDomainList);
        TourPackagePresenterImpl tourPackagePresenter = new TourPackagePresenterImpl();
        observableTourPackageList.addObserver(presenterObserver);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                tourPackageDomainList = tourPackageDao.getTourPackages();


                if (tourPackageDomainList.isEmpty()) {
                    Call<List<TourPackageResponse>> tourPackageResponseCall = RestClient.call().fetchTourPackages();
                    tourPackageResponseCall.enqueue(new Callback<List<TourPackageResponse>>() {


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
                            observableTourPackageList.changeDataset(tourPackageDomainList);
                        }

                        @Override
                        public void onFailure(Call<List<TourPackageResponse>> call, Throwable t) {

                        }
                    });
                } else {
                    observableTourPackageList.changeDataset(tourPackageDomainList);
                }
            }
        });


    }

//
//
//        tourPackageDomainObservableArrayList.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<TourPackageDomain>>() {
//            @Override
//            public void onChanged(ObservableList<TourPackageDomain> sender) {
//                onTourPackageListFinishListener.onSuccess(tourPackageDomainList);
//            }
//
//            @Override
//            public void onItemRangeChanged(ObservableList<TourPackageDomain> sender, int positionStart, int itemCount) {
//                onTourPackageListFinishListener.onSuccess(tourPackageDomainList);
//            }
//
//            @Override
//            public void onItemRangeInserted(ObservableList<TourPackageDomain> sender, int positionStart, int itemCount) {
//                onTourPackageListFinishListener.onSuccess(tourPackageDomainList);
//            }
//
//            @Override
//            public void onItemRangeMoved(ObservableList<TourPackageDomain> sender, int fromPosition, int toPosition, int itemCount) {
//                onTourPackageListFinishListener.onSuccess(tourPackageDomainList);
//            }
//
//            @Override
//            public void onItemRangeRemoved(ObservableList<TourPackageDomain> sender, int positionStart, int itemCount) {
//                onTourPackageListFinishListener.onSuccess(tourPackageDomainList);
//            }
//        });
//
//
//

//

//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {uyj
//                tourPackageDomainList = tourPackageDao.getTourPackages();
//                tourPackageDomainObservableArrayList.addAll(tourPackageDomainList);
//
//
//                if (tourPackageDomainList.isEmpty()) {
//                    Call<List<TourPackageResponse>> tourPackageResponseCall = RestClient.call().fetchTourPackages();
//                    tourPackageResponseCall.enqueue(new Callback<List<TourPackageResponse>>() {
//                        void insert(){
//                            AsyncTask.execute(new Runnable() {
//                                @Override
//                                public void run() {
//                                    tourPackageDao.insertTourPackages(tourPackageDomainList);
//                                }
//                            });
//
//                        }
//
//                        @Override
//                        public void onResponse(Call<List<TourPackageResponse>> call, Response<List<TourPackageResponse>> response) {
//                            if (response.body() != null) {
//
//                                tourPackageDomainList = new ArrayList<>(response.body().size());
//
//                                List<TourPackageResponse> tourPackageResponseList = response.body();
//                                for (TourPackageResponse tourPackageResponse : tourPackageResponseList) {
//                                    tourPackageDomainList.add(new TourPackageDomain(
//                                            tourPackageResponse.getId(),
//                                            tourPackageResponse.getName(),
//                                            tourPackageResponse.getAverageReviewScore(),
//                                            tourPackageResponse.getRegion()
//                                    ));
//
//                                }
//                                insert();
//                                tourPackageDomainObservableArrayList.addAll(tourPackageDomainList);
//                            } else {
//                                onTourPackageListFinishListener.onFailure();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<List<TourPackageResponse>> call, Throwable t) {
//
//                        }
//                    });
//                } else {
//                    tourPackageDomainObservableArrayList.addAll(tourPackageDomainList);
//                }
//            }
//        });


//


    @Override
    public void getTourPackage(OnTourPackageFinishListener onTourPackageFinishListener, String tourPackageId) {

    }
}
