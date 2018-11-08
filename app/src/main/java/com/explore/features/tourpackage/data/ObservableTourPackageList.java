package com.explore.features.tourpackage.data;

import com.explore.features.tourpackage.domain.TourPackageDomain;

import java.util.ArrayList;
import java.util.List;

public class ObservableTourPackageList extends TourPackageObservable {

    private List<TourPackageDomain> tourPackageDomainList = new ArrayList<>();

    public List<TourPackageDomain> getTourPackageDomainList() {
        return tourPackageDomainList;
    }

    public void setTourPackageDomainList(List<TourPackageDomain> tourPackageDomainList) {
        this.tourPackageDomainList = tourPackageDomainList;
    }


    public void changeDataset(List<TourPackageDomain> tourPackageDomainList) {
        this.tourPackageDomainList = tourPackageDomainList;
        setChanged();
        notifyObservers(tourPackageDomainList);
    }
}
