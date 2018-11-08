package com.explore.features.reviewnew.domain;

import android.content.Context;

import com.explore.base.PresenterObserver;
import com.explore.features.reviewnew.presentation.ReviewNewUI;

public interface ReviewInteractor {

    void getReviewList(PresenterObserver presenterObserver, Context context, String tourPackageId);

    void setReviewNew(ReviewInteractor.OnReviewSubmitListener onReviewSubmitListener, ReviewNewUI reviewNewUI);

    interface OnReviewSubmitListener {

        void onSuccess();

        void onFailure();
    }
}
