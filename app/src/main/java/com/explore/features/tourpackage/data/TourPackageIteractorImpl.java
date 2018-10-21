package com.explore.features.tourpackage.data;

import com.explore.features.tourpackage.domain.TourPackageDomain;
import com.explore.features.tourpackage.domain.TourPackageIteractor;

import java.util.ArrayList;
import java.util.List;

public class TourPackageIteractorImpl implements TourPackageIteractor {

    @Override
    public void getTourPackages(OnTourPackageFinishListener listener) {
        List<TourPackageDomain> tourPackageDomainList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            tourPackageDomainList.add(new TourPackageDomain(String.valueOf(i), "Name" + i, i * 0.5, "red", "blue"));
        }
        listener.onSuccess(tourPackageDomainList);
    }

    @Override
    public void getFilteredTourPackage() {

    }
}
