package com.explore.features.tourpackage.domain;

import java.util.List;

public interface TourPackageInteractor {

    void getTourPackages(OnTourPackageListFinishListener onTourPackageListFinishListener);

    void getTourPackage(OnTourPackageFinishListener onTourPackageFinishListener, String tourPackageId);

    interface OnTourPackageListFinishListener {
        void onSuccess(List<TourPackageDomain> tourPackageDomainList);

        void onFailure();
    }

    interface OnTourPackageFinishListener {
        void onTourPackageSuccess(TourPackageDomain tourPackageDomain);

        void onFailure();
    }
}
