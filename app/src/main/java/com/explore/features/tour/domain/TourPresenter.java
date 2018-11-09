package com.explore.features.tour.domain;

import android.content.Context;

public interface TourPresenter {

    void getTourPackage(Context context, String tourPackageId, boolean userRefresh);

    void getTourList(Context context, String tourPackageId, boolean userRefresh);

    void getTourPackageReviews(Context context, String tourPackageId, boolean userRefresh);

}
