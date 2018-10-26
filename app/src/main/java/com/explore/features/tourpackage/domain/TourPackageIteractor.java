package com.explore.features.tourpackage.domain;

import java.util.List;

public interface TourPackageIteractor {

    void getTourPackages(OnTourPackageListFinishListener onTourPackageListFinishListener);

    void getTourPackage(OnTourPackageFinishListener onTourPackageFinishListener, String tourPackageId);

    void getFilteredTourPackage();

    interface OnTourPackageListFinishListener {
        void onSuccess(List<TourPackageDomain> tourPackageDomainList);

        void onFailure();
    }

    interface OnTourPackageFinishListener {
        void onSuccess(TourPackageDomain tourPackageDomainList);

        void onFailure();
    }
}
