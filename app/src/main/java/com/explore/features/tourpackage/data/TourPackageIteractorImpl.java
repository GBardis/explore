package com.explore.features.tourpackage.data;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.explore.base.ExploreDatabase;
import com.explore.features.tourpackage.domain.TourPackageDomain;
import com.explore.features.tourpackage.domain.TourPackageIteractor;
import com.explore.rest.RestClient;
import com.explore.rest.responses.TourPackageResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TourPackageIteractorImpl implements TourPackageIteractor {

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

    @Override
    public void getTourPackage(OnTourPackageFinishListener onTourPackageFinishListener, String tourPackageId) {

    }
}
