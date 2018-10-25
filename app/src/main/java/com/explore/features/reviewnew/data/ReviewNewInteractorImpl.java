package com.explore.features.reviewnew.data;

import com.explore.features.reviewnew.domain.ReviewNewInteractor;
import com.explore.features.reviewnew.presentation.ReviewNewUI;

public class ReviewNewInteractorImpl implements ReviewNewInteractor {

    @Override
    public void setReviewNew(OnReviewNewSubmitListener onReviewNewSubmitListener, ReviewNewUI reviewNewUI) {
        onReviewNewSubmitListener.onSuccess();
    }
}
