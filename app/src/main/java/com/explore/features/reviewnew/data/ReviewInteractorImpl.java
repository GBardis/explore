package com.explore.features.reviewnew.data;

import android.content.Context;

import com.explore.features.reviewnew.domain.ReviewDomain;
import com.explore.features.reviewnew.domain.ReviewInteractor;
import com.explore.features.reviewnew.presentation.ReviewNewUI;
import com.explore.features.tourpackage.PresenterObserver;

import java.util.ArrayList;

import timber.log.Timber;

public class ReviewInteractorImpl implements ReviewInteractor {

    @Override
    public void getReviewList(PresenterObserver presenterObserver, Context context, String tourPackageId) {
    }

    @Override
    public void setReviewNew(ReviewInteractor.OnReviewSubmitListener onReviewSubmitListener, ReviewNewUI reviewNewUI) {
        ReviewDomain reviewDomain = new ReviewDomain("1", reviewNewUI.getTitle(),
                reviewNewUI.getRating(), reviewNewUI.getReviewMessage());

        //Database Call

        onReviewSubmitListener.onSuccess();
    }
}
