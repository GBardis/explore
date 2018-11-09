package com.explore.features.reviewnew.domain;

import android.content.Context;

import com.explore.base.PresenterObserver;
import com.explore.features.reviewnew.presentation.ReviewNewUI;

public interface ReviewInteractor {

    void getReviewList(PresenterObserver presenterObserver, Context context, String tourPackageId, boolean userRefresh);

    void postReview(ReviewInteractor.OnReviewSubmitListener onReviewSubmitListener, ReviewNewUI reviewNewUI, String tourPackageId);

    interface OnReviewSubmitListener {

        void onSuccess(String successToast);

        void onFailure();
    }
}
