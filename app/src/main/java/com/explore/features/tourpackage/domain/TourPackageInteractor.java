package com.explore.features.tourpackage.domain;

import android.content.Context;

import com.explore.base.PresenterObserver;

public interface TourPackageInteractor {

    void getTourPackages(PresenterObserver presenterObserver, Context context);

//    void getTourPackage(PresenterObserver presenterObserver, String tourPackageId);
}
