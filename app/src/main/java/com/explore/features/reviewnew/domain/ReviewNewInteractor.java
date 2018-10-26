package com.explore.features.reviewnew.domain;

import com.explore.features.reviewnew.presentation.ReviewNewUI;

public interface ReviewNewInteractor {

    void setReviewNew(OnReviewNewSubmitListener onReviewNewSubmitListener, ReviewNewUI reviewNewUI);

    interface OnReviewNewSubmitListener {
        void onSuccess();

        void onFailure();
    }
}
