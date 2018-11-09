package com.explore.features.reviewnew.presentation;

import android.content.Context;

import com.explore.base.PresenterObserver;
import com.explore.features.reviewnew.data.ReviewInteractorImpl;
import com.explore.features.reviewnew.domain.ReviewInteractor;
import com.explore.features.reviewnew.domain.ReviewPresenter;

import lombok.Getter;
import lombok.Setter;

public class ReviewPresenterImpl extends PresenterObserver implements ReviewPresenter, ReviewInteractor.OnReviewSubmitListener {

    @Getter
    @Setter
    private ReviewNewView mReviewNewView;
    @Getter
    @Setter
    private ReviewInteractor mReviewInteractor;

    private Context context;

    public ReviewPresenterImpl(Context context, ReviewNewView reviewNewView) {
        this.mReviewNewView = reviewNewView;
        this.mReviewInteractor = new ReviewInteractorImpl();
        this.context = context;
    }

    @Override
    public void onSuccess(String successToast) {
        mReviewNewView.afterSubmit(successToast);
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void postReview(int score, String comment, String username, String tourPackageId) {
        mReviewInteractor.postReview(this, new ReviewNewUI(score, comment, username), tourPackageId);
    }
}
