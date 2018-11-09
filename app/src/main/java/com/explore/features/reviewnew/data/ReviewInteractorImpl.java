package com.explore.features.reviewnew.data;

import android.content.Context;
import android.os.AsyncTask;

import com.explore.base.ExploreDatabase;
import com.explore.base.PresenterObserver;
import com.explore.features.reviewnew.domain.ReviewDomain;
import com.explore.features.reviewnew.domain.ReviewInteractor;
import com.explore.features.reviewnew.observers.ObservableReviewList;
import com.explore.features.reviewnew.presentation.ReviewNewUI;
import com.explore.rest.RestClient;
import com.explore.rest.responses.ReviewResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class ReviewInteractorImpl implements ReviewInteractor {
    List<ReviewDomain> reviewDomainList = new ArrayList<>();
    ObservableReviewList observableReviewList = new ObservableReviewList();

    @Override
    public void getReviewList(PresenterObserver presenterObserver, Context context, final String tourPackageId, final boolean userRefresh) {
        final ReviewDao reviewDao = ExploreDatabase.getDatabase(context).reviewDao();
        observableReviewList.setReviewDomainList(reviewDomainList);
        observableReviewList.addObserver(presenterObserver);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                reviewDomainList = reviewDao.getReviews(tourPackageId);


                if (reviewDomainList.isEmpty() || userRefresh == true) {
                    reviewDomainList.clear();


                    Call<List<ReviewResponse>> reviewResponseCall = RestClient.call().fetchReviews(tourPackageId);
                    reviewResponseCall.enqueue(new Callback<List<ReviewResponse>>() {
                        //
                        private void updateReviewsListDb() {
                            Timber.tag("INTERACTOR_TOUR").d("Inserting data into DB");
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    reviewDao.updateReviewsDb(reviewDomainList);
                                }
                            });
                        }


                        @Override
                        public void onResponse(Call<List<ReviewResponse>> call, Response<List<ReviewResponse>> response) {
                            List<ReviewResponse> reviewResponseList = response.body();
                            for (ReviewResponse tourResponse : reviewResponseList) {
                                reviewDomainList.add(new ReviewDomain(
                                        UUID.randomUUID().toString(),
                                        tourPackageId,
                                        tourResponse.getComment(),
                                        tourResponse.getScore(),
                                        tourResponse.getUsername()
                                ));
                            }

                            updateReviewsListDb();
                            Timber.tag("INTERACTOR_REVIEW").d("Serving from API!");
                            observableReviewList.changeDataset(reviewDomainList);
                        }

                        @Override
                        public void onFailure(Call<List<ReviewResponse>> call, Throwable t) {

                        }
                    });


                } else {
                    Timber.tag("INTERACTOR_REVIEW").d("Serving from Database!");
                    observableReviewList.changeDataset(reviewDomainList);

                }
            }
        });

    }

    @Override
    public void postReview(final ReviewInteractor.OnReviewSubmitListener onReviewSubmitListener, ReviewNewUI reviewNewUI, String tourPackageId) {

        Call<Void> call = RestClient.call().postReview(tourPackageId, new ReviewDomain(reviewNewUI.getScore(), reviewNewUI.getComment(), reviewNewUI.getUsername()));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                onReviewSubmitListener.onSuccess("Your review is saved");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Timber.tag("MY_EXCEPTION").d(t.toString());
            }
        });
    }
}
