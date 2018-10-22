package com.explore.features.tourpackage.domain;

import java.util.List;

public interface TourPackageIteractor {

    void getTourPackages(OnTourPackageFinishListener listener);

    void getFilteredTourPackage();

    interface OnTourPackageFinishListener {
        void onSuccess(List<TourPackageDomain> tourPackageDomainList);

        void onFailure();
    }
}
