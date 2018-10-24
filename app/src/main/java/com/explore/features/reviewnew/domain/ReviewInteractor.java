package com.explore.features.reviewnew.domain;

import java.util.ArrayList;

public interface ReviewInteractor {

    void getReviewList(OnReviewListFinishListener reviewListFinishListener, String tourPackageId);

    interface OnReviewListFinishListener{
        void onSuccess(ArrayList<ReviewDomain> reviewDomainArrayList);

        void onError();
    }
}
