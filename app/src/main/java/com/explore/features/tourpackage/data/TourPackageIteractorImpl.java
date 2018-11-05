package com.explore.features.tourpackage.data;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.explore.base.ExploreDatabase;
import com.explore.features.tourpackage.domain.TourPackageDomain;
import com.explore.features.tourpackage.domain.TourPackageIteractor;
import com.explore.rest.RestClient;
import com.explore.rest.responses.TourPackageResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TourPackageIteractorImpl implements TourPackageIteractor {

    @Override
    public void getTourPackages(OnTourPackageListFinishListener listener, final Context context) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final TourPackageDao tourPackageDao = ExploreDatabase.getDatabase(context).tourPackageDao();
                List<TourPackageDomain> tourPackageDomainList = tourPackageDao.getTourPackages();

                if (tourPackageDomainList.isEmpty()) {
                    retrofit2.Call<TourPackageResponse> tourPackageResponseCall = RestClient.call().fetchTourPackages();
                    tourPackageResponseCall.enqueue(new Callback<TourPackageResponse>() {
                        @Override
                        public void onResponse(@NonNull Call<TourPackageResponse> call, @NonNull final Response<TourPackageResponse> response) {
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    TourPackageResponse results = response.body();

                                    results.getId();
                                }
                            });
                        }

                        @Override
                        public void onFailure(@NonNull Call<TourPackageResponse> call, @NonNull Throwable t) {

                        }
                    });
                }
            }
        });

//        listener.onSuccess();
    }

    @Override
    public void getTourPackage(OnTourPackageFinishListener onTourPackageFinishListener, String tourPackageId) {

    }
}
