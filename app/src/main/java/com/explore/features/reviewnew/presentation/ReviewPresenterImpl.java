package com.explore.features.reviewnew.presentation;

import com.explore.features.reviewnew.data.ReviewInteractorImpl;
import com.explore.features.reviewnew.domain.ReviewInteractor;
import com.explore.features.reviewnew.domain.ReviewPresenter;
import com.explore.features.tourpackage.PresenterObserver;

import lombok.Getter;
import lombok.Setter;

public class ReviewPresenterImpl extends PresenterObserver implements ReviewPresenter, ReviewInteractor.OnReviewSubmitListener {

    @Getter
    @Setter
    private ReviewNewView reviewNewView;
    @Getter
    @Setter
    private ReviewInteractor reviewInteractor;

    public ReviewPresenterImpl(ReviewNewView reviewNewView) {
        this.reviewNewView = reviewNewView;
        this.reviewInteractor = new ReviewInteractorImpl();
    }

    @Override
    public void onSuccess() {
        reviewNewView.afterSubmit();
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void setReviewNew(String title, float rating, String message) {
        getReviewInteractor().setReviewNew(this, new ReviewNewUI(title, message, rating));
    }
}
