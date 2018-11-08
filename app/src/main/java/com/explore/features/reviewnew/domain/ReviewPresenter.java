package com.explore.features.reviewnew.domain;

public interface ReviewPresenter {

    void postReview(int score, String comment, String username, String tourPackageId);
}
