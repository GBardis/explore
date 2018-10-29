package com.explore.features.reviewnew.domain;

import com.explore.features.reviewnew.presentation.ReviewNewUI;

import java.util.ArrayList;

public interface ReviewInteractor {

    void getReviewList(OnReviewListFinishListener reviewListFinishListener, String tourPackageId);

    void setReviewNew(ReviewInteractor.OnReviewSubmitListener onReviewSubmitListener, ReviewNewUI reviewNewUI);

    interface OnReviewSubmitListener {

        void onSuccess();

        void onFailure();
    }

    interface OnReviewListFinishListener {

        void onSuccess(ArrayList<ReviewDomain> reviewDomainArrayList);

        void onFailure();
    }
}
