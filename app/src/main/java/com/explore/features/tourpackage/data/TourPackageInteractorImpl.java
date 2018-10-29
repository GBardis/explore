package com.explore.features.tourpackage.data;

import com.explore.features.tourpackage.domain.TourPackageDomain;
import com.explore.features.tourpackage.domain.TourPackageInteractor;

import java.util.ArrayList;
import java.util.List;

public class TourPackageInteractorImpl implements TourPackageInteractor {

    @Override
    public void getTourPackages(OnTourPackageListFinishListener listener) {
        List<TourPackageDomain> tourPackageDomainList = new ArrayList<>();

        tourPackageDomainList.add(new TourPackageDomain("1", "George", 1 * 0.5, "red", "blue"));

        tourPackageDomainList.add(new TourPackageDomain("2", "Giannhs", 1 * 0.5, "red", "blue"));

        tourPackageDomainList.add(new TourPackageDomain("3", "Geo", 1.1, "red", "blue"));

        tourPackageDomainList.add(new TourPackageDomain("4", "GeorgeBardis", 1.3, "red", "blue"));

        tourPackageDomainList.add(new TourPackageDomain("5", "hahaha", 1.1, "red", "blue"));

        tourPackageDomainList.add(new TourPackageDomain("6", "xoxoxoxox", 1.1, "red", "blue"));

        tourPackageDomainList.add(new TourPackageDomain("7", "hihihihihi", 1.2, "red", "blue"));

        listener.onSuccess(tourPackageDomainList);
    }

    @Override
    public void getTourPackage(OnTourPackageFinishListener onTourPackageFinishListener, String tourPackageId) {
        // TODO: remove condition when real data comes
        if(tourPackageId == "2") {
            onTourPackageFinishListener.onTourPackageSuccess(new TourPackageDomain("2", "Giannhs", 1 * 0.5, "red", "blue","Acropolis","A tour around the city"));
        }
    }
}
