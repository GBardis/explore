package com.explore.features.tourpackage.domain;

import android.content.Context;

import com.explore.features.tourpackage.PresenterObserver;

import java.util.List;

public interface TourPackageInteractor {

    void getTourPackages(PresenterObserver presenterObserver, Context context);

    void getTourPackage(PresenterObserver presenterObserver,OnTourPackageFinishListener onTourPackageFinishListener, String tourPackageId);

    interface OnTourPackageListFinishListener {
        void onSuccess(List<TourPackageDomain> tourPackageDomainList);

        void onFailure();
    }

    interface OnTourPackageFinishListener {
        void onTourPackageSuccess(TourPackageDomain tourPackageDomain);

        void onFailure();
    }
}
