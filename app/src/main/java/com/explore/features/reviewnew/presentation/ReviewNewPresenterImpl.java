package com.explore.features.reviewnew.presentation;

import com.explore.features.reviewnew.data.ReviewNewInteractorImpl;
import com.explore.features.reviewnew.domain.ReviewNewInteractor;
import com.explore.features.reviewnew.domain.ReviewNewPresenter;

import lombok.Getter;
import lombok.Setter;

public class ReviewNewPresenterImpl implements ReviewNewPresenter, ReviewNewInteractor.OnReviewNewSubmitListener {

    @Getter
    @Setter
    private ReviewNewView reviewNewView;
    @Getter
    @Setter
    private ReviewNewInteractor reviewNewInteractor;

    public ReviewNewPresenterImpl(ReviewNewView reviewNewView) {
        this.reviewNewView = reviewNewView;
        this.reviewNewInteractor = new ReviewNewInteractorImpl();
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
        getReviewNewInteractor().setReviewNew(this, new ReviewNewUI(title, message, rating));
    }
}
