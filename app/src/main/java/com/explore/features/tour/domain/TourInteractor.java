package com.explore.features.tour.domain;

import android.content.Context;

import com.explore.base.PresenterObserver;

public interface TourInteractor {

    void getTourList(PresenterObserver presenterObserver, Context context, String tourPackageId);
}
